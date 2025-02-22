package c8;

public class MyIntStack {
	private int[] contents;
	private int tos;


	public MyIntStack(int capacity) {
		contents = new int[capacity];
		tos = -1;
	}

	public void push(int element) {
		if (isFull()) {
			throw new RuntimeException("Stack Overflow: Không thể push, stack đã đầy!");
		}
		contents[++tos] = element;
	}

	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack Underflow: Không thể pop, stack rỗng!");
		}
		return contents[tos--];
	}

	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty: Không thể peek!");
		}
		return contents[tos];
	}

	public boolean isEmpty() {
		return tos < 0;
	}

	public boolean isFull() {
		return tos == contents.length - 1;
	}

	public void display() {
		if (isEmpty()) {
			System.out.println("Stack is empty!");
			return;
		}
		System.out.print("Stack contents: ");
		for (int i = 0; i <= tos; i++) {
			System.out.print(contents[i] + " ");
		}
		System.out.println();

	}

}
