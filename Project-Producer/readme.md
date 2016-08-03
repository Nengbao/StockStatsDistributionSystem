Project - Producer
=======================

This program is to load and parse files from data/inbox, push valid data to project_raw_data message queue and invalid data to project_invalid_data message queue.


Running
-----------

To run the example:

    mvn compile exec:java -Dexec.mainClass=camelinaction.Producer
    
When the program is done, then you can see that the file has been loaded from `data/inbox` to `jms:queue:project_raw_data` and `jms:queue;project_invalid_data`.

