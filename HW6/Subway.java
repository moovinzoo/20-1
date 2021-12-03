import java.io.*;
import java.util.*;

public class Subway {

    /* Constant values */
    public final static int MAX_TIME = 100_000_000; // 역간 최대 소요시간
    public final static int MAX_N_OF_VERTEX = 100_000; // 총 역의 수
    public final static int MAX_N_OF_EDGE = 1_000_000; // 총 간선의 수
    public final static int MAX_ADJACENCY = 100; // 한 역에 지나는 노선의 수

    /* Data Structures */
    // 해시맵#1 : 역이름과 역번호의 리스트를 매핑.
    public static HashMap<String, LinkedList<String>> mapNameToNumberList = new HashMap<>();
    // 해시맵#2 : 역번호와 Station 클래스(Vertex)를 매핑.
    public static HashMap<String, Station> mapNumberToStation = new HashMap<>();
    // 해시맵#3 : 역이름과 Station 클래스(Vertex)를 매핑.
    public static HashMap<String, Station> mapNameToStation = new HashMap<>();


    public static void main(String[] args) {
//
//        long start = System.currentTimeMillis();

        // 데이터 파일을 읽어서 맵들을 초기화한다.
        readData(args[0]);
        // 한줄씩 command(System.in)를 처리한다..
        processCommand();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


//        /* Test */
//        insertStation("820", "복정", "8");
//        insertStation("1222", "복정", "K2");
//        insertStation("1221", "수서", "K2");
//        insertStation("826", "모란", "8");
//        insertStation("830", "죽전", "8");
//        insertRail("820", "826", "3");
//        insertRail("1222", "1221", "4");
//        insertRail("826", "830", "5");
////        Station currStation = mapNameToStation.get("복정");
////        System.out.println(currStation.toString());
//
//        searchPath("죽전", "수서");
//
//        long end = System.currentTimeMillis();
//        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }

    private static void readData(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            // First, Insert Stations.
            while (!(line = br.readLine()).isBlank()) {
//                if (line.length() == 0) break;
                String[] currStationParams = line.split(" ");
                insertStation(currStationParams[0], currStationParams[1], currStationParams[2]);
            }

            // And then, Insert Rails.
            while ((line = br.readLine()) != null) {
                String[] currRailParams = line.split(" ");
                insertRail(currRailParams[0], currRailParams[1], currRailParams[2]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCommand() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;

            // First, Insert Stations.
            while ((line = br.readLine()) != null) {
                if (line.equals("QUIT")) break;
                String[] currCommandParams = line.split(" ");
                searchPath(currCommandParams[0], currCommandParams[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertStation(String stationNumber, String stationName, String line) {
        try {
            // 해당하는 역 이름이 처음 삽입된다 : StationNumber List를 초기화한 뒤 추가한다.
            if (!mapNameToNumberList.containsKey(stationName)) {
                // 해시맵#1에 새로운 (역이름, {역번호})를 삽입한다.
                mapNameToNumberList.put(stationName, new LinkedList<String>());
                mapNameToNumberList.get(stationName).add(stationNumber);

                // 해시맵#2에 (새로운 역번호, 새로운 Station)를 삽입한다.
                Station newStation = new Station(stationNumber, stationName, line);
                mapNumberToStation.put(stationNumber, newStation);

                // 해시맵#3에 (새로운 역이름, 새로운 Station)을 삽입한다.
                mapNameToStation.put(stationName, newStation);

            // 해당하는 역 이름이 이미 존재한다 : 환승역이라는 뜻이므로 StationNumber List에 추가한다.
            } else {
                // 해시맵#1의 Key:역이름 에 해당하는 Value에 {..., 새로운 역번호}를 삽입한다.
                mapNameToNumberList.get(stationName).add(stationNumber);

                // 해시맵#3에서 역이름에 해당하는 Station 객체를 찾아서 추가된 line을 반영한다.
                Station currStation = mapNameToStation.get(stationName);
                currStation.addLineOnStation(stationNumber, line);

                // 해시맵#2에 (새로운 역번호, 역이름에 해당하는 Station)를 삽입한다.
                mapNumberToStation.put(stationNumber, currStation);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static void insertRail(String origin, String destination, String elapsedTime) {
        Station origStation = mapNumberToStation.get(origin);
        Station destStation = mapNumberToStation.get(destination);
        Rail newRail = new Rail(origStation, destStation, elapsedTime);
        origStation.addRail(newRail);
    }

    /* 종각 낙성대
    종각 시청 [서울역] 숙대입구 삼각지 신용산 이촌 동작 총신대입구 [사당] 낙성대
    33
    */
    private static void searchPath(String origin, String destination) {
        // Set initial variables
        Station origStation = mapNameToStation.get(origin);
        Station destStation = mapNameToStation.get(destination);
        int numberOfStations = mapNameToStation.size();

        // Dijkstra 방식으로 탐색을 시작.
        initStationDistance(); // 모든 Station(Vertex)의 minDistance를 최대값으로 초기화한다.

        Stack<Station> stationStack = new Stack<>();
        Stack<Rail> railStack = new Stack<>(); // Path tracking을 위해서
        origStation.setMinDistance(0);
        stationStack.push(origStation); // Process 'A' 시작

        try {
            // dest에 닿을 때까지 순회 ; Process 'Dest'까지 수행
            while (stationStack.peek() != destStation) {
                // 가장 최근에 추가된 Station으로 접근
                Station start = stationStack.peek();
                int bottomDistance = start.getMinDistance();

                // 가장 최근 Station이 갖는 도착역과 Rail들을 List로 만든다.
                LinkedList<Station> endList = new LinkedList<>();
                LinkedList<Rail> edgeList = start.getRailList();

                // Rail을 순회하면서
                for (Rail e : edgeList) {
                    // 아직 지나간 적 없는 Rail(Edge)에 접근한다.
                    if (!e.getVisited()) {
                        // 현재 Station 까지 걸린 시간과 현재 Rail의 elapsedTime을 더한 값을 저장한다.
                        int tmpMinDistance = bottomDistance + e.getElapTime();
                        // 위 값이 도착지점의 minDistance보다 작으면 도착 Station의 값을 갱신한다.
                        if (e.getDestStation().getMinDistance() > tmpMinDistance) {
                            e.getDestStation().setMinDistance(tmpMinDistance);
                        }
                        endList.add(e.getDestStation());
                    }
                }

                // start Vertex에 인접한, 아직 방문되지 않은 vertex가 존재하는 경우
                if (!endList.isEmpty()) {

                    Station minDistanceEnd = endList.getFirst();

                    // 가장 짧은 거리의 adjacent vertex를 찾는다.
                    for (Station end : endList) {
                        if (end.getMinDistance() < minDistanceEnd.getMinDistance()) {
                            minDistanceEnd = end;
                        }
                    }

                    for (Rail e : edgeList) {
                        if (e.getDestStation() == minDistanceEnd) {
                            e.setVisited();
                            railStack.push(e);//TODO
                        }
                    }

                    // stationStack에 가장 짧은 인접 vertex를 넣고 그 vertex로 이동한다.
                    stationStack.push(minDistanceEnd);

                    // start Vertex에 인접한 vertex를 모두 방문한 경우
                } else {
                    // stationStack에서 현재 vertex를 제거하고, 이전 층위로 돌아간다.
                    stationStack.pop();
                    railStack.pop(); //TODO
                }
//            // Edge들을 순회하면서
//            for (Rail e : v.getRailList()) {
//                adjacencyList = v.getRailList()
//                // 아직 접근하지 않은 Vertex를 선정
//                if (e.getDestStation().getMinDistance() == MAX_TIME) { // Unvisited yet.
//                    stationStack.push(e.getDestStation());
//                }
//            }
            }
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

        // dest에 닿은 후엔, path를 만들고 걸린 시간을 출력한다.
        System.out.println(trackAndMakePath(railStack, origin));
//
//        for (Station v : mapNameToStation.values()) {
//            // 순회하다, destStation에 닿으면 끝난다.
//            if (v == destStation) break;
//            int currDistance = v.getMinDistance();
//            LinkedList<Rail> edges = v.getRailList();
//
//        }

        // 전순회하면서 전체 Stations에 대한 표가 필요
        // row0에 Starting station이 위치하는 것이 제일 좋다.
//        mapNameToStation.
    }

    private static int trackAndMakePath(Stack<Rail> railStack, String origin) {

        ListIterator<Rail> li = railStack.listIterator();
        StringBuffer sb = new StringBuffer();
        int traverseTime = 0;

        try {
            // 시작지점을 담고 시작한다.
            sb.append(origin);

            // 이제부터 railStack을 순회하면서 도착지를 담는다.
            while (li.hasNext()) {
                Rail currRail = li.next();
                sb.append(" ");

                // 다음 Rail이 존재하면, 지금 역이 환승역일 가능성이 존재한다.
                if (li.hasNext()) {
                    // 다음 Rail을 미리 보고,
                    Rail nextRail = li.next();
                    if (!currRail.getLine().equals(nextRail.getLine())) {
                        // line이 바뀌면, 환승역을 지난 것이니 []로 감싸준다.
                        sb.append("[" + currRail.getDestStation() + "]");
                    } else {
                        // line이 같으면 그냥 추가해준다.
                        sb.append(currRail.getDestStation());
                    }

                    // 다시 현재 Rail로 돌아간다.
                    li.previous();

                // 다음 Rail이 존재하지 않으면, 현재 Rail의 도착역이 환승역일 가능성이 없다.
                } else {
                    sb.append(currRail.getDestStation());
                }

                traverseTime += currRail.getElapTime();
            }

            System.out.println(sb.toString());

            return traverseTime;

        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

        return -1;
    }
//    private static void searchPath(String orig, String dest) {
//        // Make adjacencyTable that stores distance from origin station.
//        HashMap<Station, Integer> distanceFromOrigin = new HashMap<>();
//        Station origStation = mapNameToStation.get(orig);
//        Station destStation = mapNameToStation.get(dest);
//
//        // Initialize.
//        initStationDistance();
//        distanceFromOrigin.put(origStation, 0); // From orig to orig itself
//
//        // Process(in terms of Dijkstra algorithm).
//
//        // 현재까지 거리가 갱신된 Station 중에서 가장 짧은 거리인 것을 선택
//        Station minDistanceStationAmongNotHasBeenVisited = new Station();
//        for (Station s : distanceFromOrigin.keySet()) {
//            if (s.isVisited() == false) {
//                if (minDistanceStationAmongNotHasBeenVisited.getMinDistance() > s.getMinDistance()) {
//                    minDistanceStationAmongNotHasBeenVisited = s;
//                }
//            }
//        }
//
//        // 그 Station을 방문
//        // 현재 Station에 인접한 Station들에 대한 distance를 갱신
//
//
//
//        if (currStation == mapNameToStation.get)
//
//
//
//
//
////        for (Station v : mapNameToStation.values()) {
////            if (v == mapNameToStation.get(orig)) {
////
////            } else {
////
////            }
////        }
//
//
//    }

    private static void initStationDistance() {
        for (Station currStation : mapNameToStation.values()) {
            currStation.setMinDistance(MAX_TIME);
            currStation.setVisited(false);
            for (Rail currRail : currStation.getRailList()) {
                currRail.setUnvisited();
            }
        }
    }
}