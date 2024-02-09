/**
 * @author Kelly Gorman
 * class that adds additional functionalities to DualImplementationBag instances
 */
public class DemoBag {
  
  /* 
   * Removes all instances of an element from a bag, retaining all else
   * @param bag       DIB to have elements removed
   * @param element   Element to be removed from DIB
   */
  public <T> void removeAll(DualImplementationBag<T> bag, T element) {
    for(int i = 0; i < bag.size(); i++) {
      if(bag.get(i).equals(element)) {
        bag.remove(bag.get(i));
        i--;
      }
    }
  }
  
  /*
   * Retain all instances of an element in a bag, remove all else
   * @param   bag DIB to have elements retained
   * @param   element Element to be retained in bag
   */
  public <T> void retainAll(DualImplementationBag<T> bag, T element) {
    for(int i = 0; i < bag.size(); i++) {
      if(!bag.get(i).equals(element)) {
        bag.remove(bag.get(i));
        i--;
      }
    }
  }
  
  /*
   * Add all elements from both bags into one new bag
   * @param otherBag1  First bag to have elements added to new bag
   * @param otherBag2  Second bag to have elements added to new bag
   * @return           Bag created from adding all elements of first two
   */
  public <T> DualImplementationBag<T> union(DualImplementationBag<T> otherBag1, 
               DualImplementationBag<T> otherBag2) {
    DualImplementationBag<T> newBag = new DualImplementationBag<T>(true);
    for(int i = 0; i < otherBag1.size(); i++) {
      newBag.add(otherBag1.get(i));
    }
    for(int i = 0; i < otherBag2.size(); i++) {
      newBag.add(otherBag2.get(i));
    }
    return newBag;
  }
  
  /*
   * Create new bag that only includes objects that appear in both bags
   * Note: if elements appear more than once in one bag and are found at least  once in the other bag, 
   *       (for example, twice in otherBag1 and once in otherBag2) they will appear the number of times 
   *       they appear in the greatest instance (in this example, twice).
   * @param otherBag1  First bag to check for elements common to both
   * @param otherBag2  Second bag to check for elements common to both
   * @return           Bag containing elements that appear in both bags
   */
  public <T> DualImplementationBag<T> intersection(DualImplementationBag<T> otherBag1, 
                      DualImplementationBag<T> otherBag2) {
    DualImplementationBag<T> newBag = new DualImplementationBag<T>(true);
    for(int i = 0; i < otherBag1.size(); i++) {
      for(int j = 0; j < otherBag2.size(); j++) {
        if(otherBag1.get(i).equals(otherBag2.get(j))) {
          newBag.add(otherBag1.get(i));
        }
      }
    }
    return newBag;
  }
  
  /*
   * Create a new bag that only contains elements found in first bag but not second bag
   * Note: if an element appears more than once in otherBag1 but not at all in otherBag2, it 
   *       will be added the number of times it appears in otherBag1. If an element appears more than once
   *       in otherBag1 but at least once in otherBag2, it will not be added at all.
   * @param otherBag1  Bag whose elements must be not matched in second parameter
   * @param otherBag2  If elements in this bag match an element from first bag, it won't be added to new bag
   * @return           New bag with elements that only appear in otherBag1 but not otherBag2
   */
  public <T> DualImplementationBag<T> difference(DualImplementationBag<T> otherBag1, 
                    DualImplementationBag<T> otherBag2) {
    DualImplementationBag<T> newBag = new DualImplementationBag<T>(true);
    for(int i = 0; i < otherBag1.size(); i++) {
      newBag.add(otherBag1.get(i));
    }
    for(int i = 0; i < otherBag2.size(); i++) {
      for (int j = 0; j < newBag.size(); j++) {
        if (newBag.get(j).equals(otherBag2.get(i))) {
          newBag.remove(newBag.get(j));
        }
      }
    }
    return newBag;
  }
  
}