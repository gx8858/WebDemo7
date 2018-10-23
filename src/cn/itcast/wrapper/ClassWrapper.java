package cn.itcast.wrapper;

import org.junit.Test;

public class ClassWrapper {
	@Test
	public void fun1(){
		SuperPerson s = new SuperPerson(new Person());
		s.run();
	}
}


/**
 * 增强类与被增强类实现相同的接口
 * 增强类中获取被增强类的引用
*/
interface IPerson{
	public void run();
}

// 被增强的类
class Person implements IPerson{
	public void run(){
		System.out.println("跑...");
	}
}

// 增强的类
class SuperPerson implements IPerson{
	private IPerson p;
	public SuperPerson(IPerson p){
		this.p = p;
	}
	public void run() {
		p.run();
		System.out.println("超人跑...");
	}
}


/*class person{
	public void run(){
		System.out.println("跑...");
	}
}

class SuperPerson extends person{
	public void run() {
		// super.run();
		System.out.println("超人跑...");
	}
}*/


