/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Cell {
    public String state;
    public int id;

    public Cell(int id) {
        this.state = "blocked";
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }
}
