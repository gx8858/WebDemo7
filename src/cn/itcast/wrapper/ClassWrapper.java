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
 * ��ǿ���뱻��ǿ��ʵ����ͬ�Ľӿ�
 * ��ǿ���л�ȡ����ǿ�������
*/
interface IPerson{
	public void run();
}

// ����ǿ����
class Person implements IPerson{
	public void run(){
		System.out.println("��...");
	}
}

// ��ǿ����
class SuperPerson implements IPerson{
	private IPerson p;
	public SuperPerson(IPerson p){
		this.p = p;
	}
	public void run() {
		p.run();
		System.out.println("������...");
	}
}


/*class person{
	public void run(){
		System.out.println("��...");
	}
}

class SuperPerson extends person{
	public void run() {
		// super.run();
		System.out.println("������...");
	}
}*/


