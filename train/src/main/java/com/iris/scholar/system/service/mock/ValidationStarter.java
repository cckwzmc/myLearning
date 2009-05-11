package com.iris.scholar.system.service.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.iris.scholar.system.service.SOAPMessage;
import com.iris.scholar.system.service.ThreadPoolService;
import com.iris.scholar.system.service.ValidationService;


/**
 * 模拟执行这个环境的验证
 * 
 * @author DigitalSonic
 */
public class ValidationStarter implements Runnable {
    private List<String>      entries;
    private ValidationService validationService;
    private CountDownLatch    signal;

    public ValidationStarter(List<String> entries, ValidationService validationService,
            CountDownLatch signal) {
        this.entries = entries;
        this.validationService = validationService;
        this.signal = signal;
    }

    /**
     * 线程池大小为10，初始化执行一次，随后并发三个验证
     */
    public static void main(String[] args) {
        ThreadPoolService threadPoolService = new ThreadPoolService(10);
        ValidationService validationService = new ValidationService(threadPoolService);
        List<String> entries = new ArrayList<String>();
        CountDownLatch signal = new CountDownLatch(3);
        long start;
        long stop;

        for (SOAPMessage node : MockNodeValidator.ENTRIES) {
            entries.add(node.getWsdl());
        }

        start = System.currentTimeMillis();


        threadPoolService.execute(new ValidationStarter(entries, validationService, signal));
        threadPoolService.execute(new ValidationStarter(entries, validationService, signal));
        threadPoolService.execute(new ValidationStarter(entries, validationService, signal));

        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        validationService.validate(entries);
        stop = System.currentTimeMillis();
        threadPoolService.destoryExecutorService(1000);
        System.out.println("实际执行验证次数: " + MockNodeValidator.getCount());
        System.out.println("实际执行时间: " + (stop - start) + "ms");
    }

    @Override
    public void run() {
        validationService.validate(entries);
        signal.countDown();
    }

}
