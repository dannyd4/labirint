
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class labirint {

  public static void main(String[] args) {
    
    var lb = new Labir();
    var lp = new LabirintPrinter();
    var wm = new WaveMethod(lb.getLab());

    System.out.println(lp.numLab(lb.getLab())); // Лабиринт 
    wm.Colorize(new Point(6, 7)); // Реализация волного метода от точки входа в лабиринт 
    System.out.println(lp.numLab(lb.getLab())); // Лабиринт после реализации волного метода
    wm.getWay(new Point(13, 8)); // Поиск кротчайшего пути от выхода к входу ()
    System.out.println(lp.numLab(lb.getLab()));  // Лабиринт с путем выхода (путь обозначен "0")   
  }
}

class Point {
  int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return String.format("x: %d  y: %d", x, y);
  }
}  
    
class Labir {
  int[][] lab;

  public Labir() {
    int[][] lab = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1, 00, -1, },
        { -1, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, -1, 00, -1, },
        { -1, 00, -1, -1, -1, 00, 00, -1, 00, 00, 00, -1, 00, -1, },
        { -1, 00, 00, 00, -1, 00, -1, -1, -1, -1, 00, -1, 00, -1, },
        { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, 00, -1, 00, -1, },
        { -1, -1, -1, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, -1, },
        { -1, 00, -1, 00, -1, 00, -1, -1, 00, -1, -1, -1, 00, -1, },
        { -1, 00, -1, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, -1, },
        { -1, 00, -1, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, -1, },
        { -1, 00, -1, 00, -1, -1, -1, -1, -1, -1, 00, 00, 00, -1, },
        { -1, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1, },
        { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, -1, 00, 00, -1, },
        { -1, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, 00, 00, -1, },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, }
    };

    this.lab = lab;
  }

  public int[][] getLab() {
    return lab;
  }
}

class LabirintPrinter {

  public String numLab(int[][] lab) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < lab.length; i++) {
      for (int j = 0; j < lab[i].length; j++) {           
        sb.append(String.format("%3d", lab[i][j]));
      }
      sb.append("\n");
    }
    for (int i = 0; i < 3; i++) {
      sb.append("\n");
    }
    return sb.toString();
  }  
}

class WaveMethod {
  int[][] lab;

  public WaveMethod(int[][] lab) {
    this.lab = lab;
  }

  public void Colorize(Point startPoint) {
    Queue<Point> queue = new LinkedList<Point>();
    queue.add(startPoint);

    lab[startPoint.x][startPoint.y] = 1;    
    while (queue.size() != 0) {
      Point p = queue.remove(); 
             

      if (lab[p.x - 1][p.y] == 0) {
        queue.add(new Point(p.x - 1, p.y));
        lab[p.x - 1][p.y] = lab[p.x][p.y] + 1;        
      }
      if (lab[p.x][p.y - 1] == 0) {
        queue.add(new Point(p.x, p.y - 1));
        lab[p.x][p.y - 1] = lab[p.x][p.y] + 1;        
      }
      if (lab[p.x + 1][p.y] == 0) {
        queue.add(new Point(p.x + 1, p.y));
        lab[p.x + 1][p.y] = lab[p.x][p.y] + 1;        
      }
      if (lab[p.x][p.y + 1] == 0) {
        queue.add(new Point(p.x, p.y + 1));
        lab[p.x][p.y + 1] = lab[p.x][p.y] + 1;        
      }
    }
  }

  public ArrayList<Point> getWay(Point exit) {
    ArrayList<Point> road = new ArrayList<>();
    road.add(exit);
    int numExit = lab[exit.x][exit.y];    
    Point a = exit; 
    int ex = numExit;
    lab[a.x][a.y] = 0;
    while (road.size() != numExit) {      
  
        if (lab[a.x - 1][a.y] == ex - 1) {
          road.add(new Point(a.x - 1, a.y));
          lab[a.x - 1][a.y] = 0;
          a = new Point(a.x - 1, a.y);
          ex--;          
        }
        else if (lab[a.x][a.y - 1] == ex - 1) {
          road.add(new Point(a.x, a.y - 1));
          lab[a.x][a.y - 1] = 0;
          a = new Point(a.x, a.y - 1);
          ex--;          
        }
        else if (lab[a.x + 1][a.y] == ex - 1) {
          road.add(new Point(a.x + 1, a.y));
          lab[a.x + 1][a.y] = 0;
          a = new Point(a.x + 1, a.y);          
          ex--;                    
        }
        else if (lab[a.x][a.y + 1] == ex - 1) {
          road.add(new Point(a.x, a.y + 1));
          lab[a.x][a.y + 1] = 0;
          a = new Point(a.x, a.y + 1);
          ex--;                      
        }        
      }      
      System.out.println(road); // Путь от выхода ко входу
      System.out.println();
      return road;    
  } 
}