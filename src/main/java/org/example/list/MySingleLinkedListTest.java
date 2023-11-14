package org.example.list;

public class MySingleLinkedListTest {


	public static void main(String[] args) {

		MySingleLinkedList<Integer> l = new MySingleLinkedList<>();

		System.out.println(l);

		System.out.println();
		System.out.println();
		System.out.println();

		l.delete(1);
		l.insert(2);
		l.delete(1);

		System.out.println(l);
		l.delete(2);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);


		l.insert(1);
		l.insert(2);
		l.insert(3);
		l.insert(4);
		l.insert(5);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);



		l.update(2, 10);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);


		l.delete(10);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);



		l.delete(1);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);




		l.delete(5);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);


// index out of bound
//		l.update(2, 7);
//
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println(l);


		l.update(0, 7);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l.search(7));

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(l.search(4));
	}

}
