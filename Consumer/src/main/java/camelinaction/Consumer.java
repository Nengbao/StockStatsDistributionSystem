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

import java.text.DecimalFormat;

public class Consumer {

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
			Stock microsoftStock = new Stock("MSFT");
			Stock oracleStock = new Stock("ORCL");
			Stock ibmStock = new Stock("IBM");

			/* 	parse messages in project_raw_data queue
			 *	calculate the statistics and form new messages containing those statistics
			 *	send these 3 different stocks to 3 individual topics
			 */

			public void configure() {
				from("jms:queue:project_raw_data")
						.log("RECEIVED: ${body}")
						.choice()

						.when(new CheckExpectedStock("MSFT"))
						.process(new StockStatGenetateProcessor(microsoftStock))
						.log("Topic: ${body} added to project_topic_msft.")
						.to("jms:topic:project_topic_msft")

						.when(new CheckExpectedStock("ORCL"))
						.process(new StockStatGenetateProcessor(oracleStock))
						.log("Topic: ${body} added to project_topic_orcl.")
						.to("jms:topic:project_topic_orcl")

						.when(new CheckExpectedStock("IBM"))
						.process(new StockStatGenetateProcessor(ibmStock))
						.log("Topic: ${body} added to project_topic_ibm.")
						.to("jms:topic:project_topic_ibm")

						.otherwise()
						.to("jms:queue:project_other_stocks");
			}
		});

		// start the route and let it do its work
		context.start();
		Thread.currentThread().join();
//		Thread.sleep(20 * 1000);

		// stop the CamelContext
//		context.stop();
	}
}
