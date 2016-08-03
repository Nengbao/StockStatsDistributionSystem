/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class Producer {

	public static void main(String args[]) throws Exception {
		// create CamelContext
		CamelContext context = new DefaultCamelContext();

		// connect to ActiveMQ JMS broker listening on localhost on port 61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		context.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("file:data/inbox?noop=true")
						.log("RETRIEVED:  ${file:name}")
						.unmarshal().csv()
						.split(body())    // body() is a list, split it into multiple exchanges, each of which has an element in the list
						.log("MESSAGE: ${body}")
						.choice()
						.when(new DataValidationPredicate())
						.process(new Processor() {
							public void process(Exchange e) throws Exception {
								System.out.println("MESSAGE: " + e.getIn().getBody(String.class)
										+ " FROM FILE: " + e.getIn().getHeader("CamelFileName")
										+ " is heading to project_raw_data Queue for Stock: "
										+ (e.getIn().getBody(String.class).split("\t"))[0]);
							}
						}).to("jms:queue:project_raw_data")
						.otherwise()
						.process(new Processor() {
							public void process(Exchange e) throws Exception {
								System.out.println("MESSAGE: " + e.getIn().getBody(String.class)
										+ "FROM FILE:" + e.getIn().getHeader("CamelFileName")
										+ " is heading to project_invalid_data: "
										+ (e.getIn().getBody(String.class).split("\t"))[0]);
							}
						}).to("jms:queue:project_invalid_raw_data");
			}
		});

/*                .process(new Processor() {
					@Override
					public void process(Exchange e) throws Exception {
						System.out.println(e.getIn().getBody(String.class));
					}
                })
*/


		// start the route and let it do its work
		context.start();
		Thread.currentThread().join();
//		Thread.sleep(20 * 1000);

		// stop the CamelContext
//		context.stop();
	}
}
