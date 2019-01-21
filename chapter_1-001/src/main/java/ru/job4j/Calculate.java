package ru.job4j;

/**
* Calculate - простой класс, содежащий метод main().
* @author Gregory Smirnov (artress@ngs.ru)
* @since 12/01/2018
* @version 1.0
*/

public class Calculate {
	
	/**
	* Метод main() - выводит сообщение "Hello, world!"
	* @param args - строка входных параметров.
	*/
	public static void main(String[] args) {
		System.out.println("Hello, world!");
	}
	
	/**
	* Метод echo().
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo: " + name;
	}
	
}