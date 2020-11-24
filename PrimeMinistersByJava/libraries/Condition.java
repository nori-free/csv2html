package libraries;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Condition {

	/**
	* 保存された条件
	*/
	private Supplier<Boolean> aCondition;

	/**
	* コンストラクタ
	* @params aGotCondition 評価したい条件
	*/
	public Condition(Supplier<Boolean> aGotCondition) {
		this.aCondition = null;
		this.aCondition = aGotCondition;
	}

	/**
	* 条件が偽の時に実行する処理
	*/
	public void ifFalse(final Runnable aRunnable) {
		this.ifThenElse(() -> {}, aRunnable);
	}

	/**
	* 条件が偽の時に実行する処理
	*/
	public void ifFalse(final Consumer<Condition> aConsumer) {
		this.ifThenElse(pattern -> {}, aConsumer);
	}

	/**
	* 条件が正の時に実行する処理
	*/
	public void ifTrue(final Runnable aRunnable) {
		this.ifThenElse(aRunnable, () -> {});
	}

	/**
	* 条件が正の時に実行する処理
	*/
	public void ifTrue(final Consumer<Condition> aConsumer) {
		this.ifThenElse(aConsumer, pattern -> {});
	}

	/**
	* 条件が正の時に実行する処理と偽の時に実行する処理
	*/
	public void ifThenElse(final Runnable aRunnable, final Runnable anotherRunnable) {
		this.ifThenElse(pattern1 -> aRunnable.run(), pattern2 -> anotherRunnable.run());
	}

	/**
	* 条件が正の時に実行する処理と偽の時に実行する処理
	*/
	public void ifThenElse(final Consumer<Condition> aConsumer, final Consumer<Condition> anotherConsumer) {
		if(this.aCondition.get()){
			aConsumer.accept(this);
		}else{
			anotherConsumer.accept(this);
		}
	}
}
