package com.iris.scholar.system.service.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.iris.scholar.system.service.SOAPMessage;


/**
 * 模拟执行节点验证的Mock类
 * 
 * @author DigitalSonic
 */
public class MockNodeValidator {
    public static final List<SOAPMessage>         ENTRIES  = new ArrayList<SOAPMessage>();
    private static final Map<String, SOAPMessage> NODE_MAP = new HashMap<String, SOAPMessage>();

    private static AtomicInteger           count    = new AtomicInteger(0);
    private static Logger                  logger   = Logger.getLogger("MockNodeValidator");

    /*
     * 构造模拟数据
     */
    static {
        SOAPMessage msg0 = new SOAPMessage("NODE0"); //入口0
        SOAPMessage msg1 = new SOAPMessage("NODE1");
        SOAPMessage msg2 = new SOAPMessage("NODE2");
        SOAPMessage msg3 = new SOAPMessage("NODE3");
        SOAPMessage msg4 = new SOAPMessage("NODE4");
        SOAPMessage msg5 = new SOAPMessage("NODE5");
        SOAPMessage msg6 = new SOAPMessage("NODE6"); //入口1
        SOAPMessage msg7 = new SOAPMessage("NODE7");
        SOAPMessage msg8 = new SOAPMessage("NODE8");
        SOAPMessage msg9 = new SOAPMessage("NODE9");



        NODE_MAP.put(msg0.getMessageId(), msg0);
        NODE_MAP.put(msg1.getMessageId(), msg1);
        NODE_MAP.put(msg2.getMessageId(), msg2);
        NODE_MAP.put(msg3.getMessageId(), msg3);
        NODE_MAP.put(msg4.getMessageId(), msg4);
        NODE_MAP.put(msg5.getMessageId(), msg5);
        NODE_MAP.put(msg6.getMessageId(), msg6);
        NODE_MAP.put(msg7.getMessageId(), msg7);
        NODE_MAP.put(msg8.getMessageId(), msg8);
        NODE_MAP.put(msg9.getMessageId(), msg9);

        ENTRIES.add(msg0);
        ENTRIES.add(msg6);
    }

    /**
     * 模拟执行远程验证返回节点，每次调用等待500ms
     */
    public static SOAPMessage validateNode(String wsdl) {
        SOAPMessage node = cloneNode(NODE_MAP.get(wsdl));
        logger.info("验证节点" + node.getMessageId()+ "[" + node.getMessageId() + "]");
        count.getAndIncrement();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return node;
    }

    /**
     * 获得计数器的值
     */
    public static int getCount() {
        return count.intValue();
    }

    /**
     * 克隆一个新的Node对象（未执行深度克隆）
     */
	// public static SOAPMessage cloneNode(SOAPMessage originalNode) {
	// SOAPMessage newNode = new SOAPMessage();
	//
	// newNode.setName(originalNode.getMessageId());
	// newNode.setWsdl(originalNode.getXmlData()getWsdl());
	// newNode.setResult(originalNode.getResult());
	// newNode.setDependencies(originalNode.getDependencies());
	//
	// return newNode;
	// }
}
