package com.lotteryCommend.exception;

public class LotteryException extends Exception {
	private static final long serialVersionUID = -8071870311158329838L;

	public LotteryException() {
		super();
	}

	public LotteryException(String message) {
		super(message);
	}

	public LotteryException(String message, Throwable t) {
		super(message, t);
	}

	public LotteryException(Throwable t) {
		super(t);
	}
}
