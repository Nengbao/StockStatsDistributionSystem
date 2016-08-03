Project - Consumer
=======================

This program is to read message from project_raw_data message queue and then calculate different statistics,
afterwards, put the new message with statistics information to a message topic for each stock.

Running
-----------

To run the example:

    mvn compile exec:java -Dexec.mainClass=camelinaction.Consumer
    
When the program is done, then you can see that messages from `jms:queue:project_raw_data` are consumed and
multiple message topics `jms:topic;project_topic_stockName` (a stockName is msft, orcl, ibm and so on).

