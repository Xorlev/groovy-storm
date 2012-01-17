package com.xorlev.storm

import backtype.storm.Config
import backtype.storm.LocalCluster
import backtype.storm.StormSubmitter
import backtype.storm.task.OutputCollector
import backtype.storm.task.TopologyContext
import backtype.storm.testing.TestWordSpout
import backtype.storm.topology.IRichBolt
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.TopologyBuilder
import backtype.storm.tuple.Fields
import backtype.storm.tuple.Values
import backtype.storm.utils.Utils

/**
 * Gleefully stolen from the storm-starter project
 * ExclamationTopology, minus the inline classes and semicolons
 * 2012-01-17
 * @author Michael Rose <michael@fullcontact.com>
 */
class GroovyTestTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder()

        builder.setSpout("word", new TestWordSpout(), 10)
        builder.setBolt("exclaim1", new ExclamationBolt(), 3)
                .shuffleGrouping("word")
        builder.setBolt("exclaim2", new ExclamationBolt(), 2)
                .shuffleGrouping("exclaim1")

        Config conf = new Config()
        conf.setDebug(true)

        if(args!=null && args.length > 0) {
            conf.setNumWorkers(3)

            StormSubmitter.submitTopology(args[0], conf, builder.createTopology())
        } else {

            LocalCluster cluster = new LocalCluster()
            cluster.submitTopology("test", conf, builder.createTopology())
            Utils.sleep(10000)
            cluster.killTopology("test")
            cluster.shutdown()
        }
    }
}
class ExclamationBolt implements IRichBolt {
    OutputCollector _collector

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        _collector = collector
    }

    @Override
    public void execute(backtype.storm.tuple.Tuple tuple) {
        _collector.emit(tuple, new Values(tuple.getString(0) + "!!!"))
        _collector.ack(tuple)
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"))
    }


}