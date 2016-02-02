package SERVICES_STREAM;

import STRATEGY_PATTERN.StreamingStrategy;

public class ContextStrategy implements SystemCommonInterface{
	
	private StreamingStrategy strategy;

	public ContextStrategy(StreamingStrategy strategy){
		this.strategy = strategy;	
	}

	public int executeStrategy(int num1, int num2){
		return strategy.doOperation(num1, num2);
	}
}
