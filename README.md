# aktos-dcs-java
Java binding example for aktos-dcs

## Running the code
Before running the code, make sure you have ZeroMQ installed and compiled for java.
Follow instructions on : http://zeromq.org/bindings:java

Also you will need to download and compile jzmq from : http://github.com/zeromq/jzmq

On linux machines you will find **zmq.jar** in : /usr/local/share/java
And other shared libraries in : /usr/local/lib

## Modifying code for own needs
Just modify the TestClass to send required pin_name value pairs to Publisher.sendMessage(pin_name, value) function.

Publisher builds message in aktos-dcs message format and send it to subscribers. Sample TestClass is a CLI which is talking with aktos-website interface via aktos-dcs python process.


## Notes
All classes of com.aktos_elektronik.json_ops package are taken from :
http://www.json.org/java/

aktos-dcs and aktos-website can be found on : http://github.com/ceremcem

"Hello World!" samples are taken from : https://dzone.com/articles/working-zeromq-java-and-jzmq

##TO-DO
* Auto-generate messages. Remove hard-coded ones. **OK**
	* Now payload of message comes from Broker, senderID is Java UUID and messageID is JavaUUID+""+Math.Random()
