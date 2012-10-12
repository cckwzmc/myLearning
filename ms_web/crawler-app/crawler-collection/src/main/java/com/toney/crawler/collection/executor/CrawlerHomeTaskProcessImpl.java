package com.toney.crawler.collection.executor;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamException;


/**
 * 实现读取数据操作.
 * @author toney.li
 *
 * @param <T>
 */

public class CrawlerHomeTaskProcessImpl<T>   implements ItemProcessor {

	@Override
	public Object process(Object item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
/**
public class CrawlerHomeTaskReaderImpl<T> implements ItemReader<T>, ItemStream {

	private final ItemReader<T> delegate;

	public CrawlerHomeTaskReaderImpl(ItemReader<T> delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (delegate instanceof ItemStream) {
			((ItemStream) this.delegate).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {

	}

	@Override
	public void close() throws ItemStreamException {

	}

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return null;
	}

}**/
