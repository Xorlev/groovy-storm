# Groovy Storm Starter

## Overview

**groovy-storm** is a starter kit for using Groovy with Nathan Marz's [Storm](https://github.com/nathanmarz/storm) distributed computing framework. Doubtless, there are better ways of doing this, but this hastily hacked together *pom.xml* and ExclamationTopology example will help get a project going.

I'll update this as I learn better ways of doing this. Perhaps even as a leiningen project.

Pull requests are always appreciated.

## Running

Should be easy as:

	$ mvn package
	[INFO] Scanning for projects...
	[INFO]                                                                         
	[INFO] ------------------------------------------------------------------------
	[INFO] Building Groovy Storm Starter 0.1.0
	[INFO] ------------------------------------------------------------------------
	.
	.
	.
	[INFO] BUILD SUCCESS
	$ java -jar target/groovy-storm-0.1.0-jar-with-dependencies.jar 
	0    [main] INFO  backtype.storm.zookeeper  - Starting inprocess zookeeper at port 2181 and dir /var/folders/pz/f8p42hsj2yvckf1zmt18d11m0000gn/T//f6bf6227-fbe2-43c4-9e6e-924093e0674c
	etc…

Output cut for brevity.

## License

Probably Eclipse, given that Storm is Eclipse. I heard lawyers have sharp teeth and wallets like hoovers.