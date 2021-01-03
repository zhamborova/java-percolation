/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Cell {
    private String state;
    private final int id;

    public Cell(int id) {
        this.state = "blocked";
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public int getId() {
        return id;
    }
}
