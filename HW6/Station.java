// Vertex로 기능하는 Station class

import java.util.LinkedList;

public class Station {
    private String name;
    private LinkedList<String> numberList; // 환승역일 경우 > 1
    private LinkedList<String> lineList; // 환승역일 경우 > 1
    private LinkedList<Rail> railList;
    private int minDistance;
    private boolean visited;

    public Station(String _number, String _name, String _line) {
        numberList = new LinkedList<>();
        numberList.add(_number);
        name = _name;
        lineList = new LinkedList<>();
        lineList.add(_line);
        railList = new LinkedList<>(); // 초기화만 해둔다.
        minDistance = Subway.MAX_TIME; // Dijkstra로 path를 찾기 위해 max value로 설정해둔다.
        visited = false;
    }

    public Station() {
        // dummy
        minDistance = Subway.MAX_TIME;
    }

    public void addLineOnStation(String _newStationNumber, String _newLine) {
        numberList.add(_newStationNumber);
        lineList.add(_newLine);
    }

    public void addRail(Rail newRail) {
        railList.add(newRail);
    }

    public String getStationName() {
        return name;
    }

    public LinkedList<String> getStationNumberList() {
        return numberList;
    }

    public LinkedList<String> getLineList() {
        return lineList;
    }

    public void setMinDistance(int distance) {
       minDistance = distance;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public boolean isVisited() {
        return visited;
    }

    public LinkedList<Rail> getRailList() {
        return railList;
    }

    @Override
    public String toString() {
        return name;
    }
    //디버깅용
//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("역이름 : ");
//        sb.append(name);
//        sb.append(", 역번호 : ");
//        sb.append(numberList.toString());
//        sb.append(", 지나가는 호선 : ");
//        sb.append(lineList.toString());
//        sb.append("\n레일 리스트 : ");
//        sb.append(railList.toString());
//
//        return sb.toString();
//    }
}
