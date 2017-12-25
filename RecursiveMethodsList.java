package recursiveList;

import java.util.ArrayList;


public class RecursiveMethodsList<T extends Comparable<T>> {

	private ArrayList<T> elements = new ArrayList<>();

	// do NOT change this method
	public void add(T newElement) {
		if (newElement == null)
			throw new IllegalArgumentException("No way will I add null to the " + "elements field- not a chance!!!");
		else
			elements.add(newElement);
	}

	private boolean isNonDecreasing(ArrayList<T> elements, int pos) {
		if (elements == null)
			throw new IllegalArgumentException();

		if (elements.size() == 0 || elements.size() == 1 || pos + 1 >= elements.size())
			return true;

		return elements.get(pos).compareTo(elements.get(pos + 1)) <= 0 && isNonDecreasing(elements, pos + 1);
	}

	public boolean isNonDecreasing() {
		return isNonDecreasing(this.elements, 0);
	}

	private T elementAfter(T element, ArrayList<T> elements, int pos) {
		if (element == null || elements == null)
			throw new IllegalArgumentException();

		if (elements.size() == 0 || pos + 1 >= elements.size())
			return null;

		if (elements.get(pos).compareTo(element) != 0) {
			return elementAfter(element, elements, pos + 1);
		}
		return elements.get(pos + 1);
	}

	public T elementAfter(T element) {
		return elementAfter(element, elements, 0);
	}

	private RecursiveMethodsList<T> elementsBetweenValues(RecursiveMethodsList<T> newList, T lowerValue, T upperValue,
			int pos) {

		if (lowerValue == null || upperValue == null)
			throw new IllegalArgumentException();

		if (pos >= elements.size())
			return newList;

		if (elements.get(pos).compareTo(lowerValue) >= 0 && elements.get(pos).compareTo(upperValue) <= 0) {
			newList.add(elements.get(pos));
		}

		return elementsBetweenValues(newList, lowerValue, upperValue, pos + 1);
	}

	public RecursiveMethodsList<T> elementsBetweenValues(T lowerValue, T upperValue) {
		RecursiveMethodsList<T> newList = new RecursiveMethodsList<T>();
		return elementsBetweenValues(newList, lowerValue, upperValue, 0);
	}

	private void replaceWithOtherList(RecursiveMethodsList<T> otherList, int startingPos, int pos) {
		if (startingPos < elements.size() && startingPos >= 0 && pos < otherList.elements.size()) {
			elements.set(startingPos, otherList.elements.get(pos));
			replaceWithOtherList(otherList, startingPos + 1, pos + 1);
		}
	}

	public void replaceWithOtherList(RecursiveMethodsList<T> otherList, int startingPos) {
		if (otherList == null)
			throw new IllegalArgumentException();
		
		if(elements.size() - startingPos >= otherList.elements.size())
			replaceWithOtherList(otherList, startingPos, 0);
	}

	private String toString(ArrayList<T> elements, int pos) {
		if (elements == null)
			throw new IllegalArgumentException();

		if (elements.size() == 0 || pos >= elements.size())
			return "";

		if (pos == elements.size() - 1)
			return elements.get(pos) + toString(this.elements, pos + 1);

		return elements.get(pos) + ", " + toString(this.elements, pos + 1);
	}

	public String toString() {
		return toString(this.elements, 0);
	}

}
