// Edge로 기능하는 Rail class

public class Rail {
    private Station origStation;
    private Station destStation;
    private int elapTime;
    private String line;
    private boolean hasVisited;

    public Rail(Station _origStation, Station _destStation, String _elapTime) {
        origStation = _origStation;
        destStation = _destStation;
        elapTime = Integer.parseInt(_elapTime);
        line = findCommonLine(origStation, destStation);
        hasVisited = false;
    }

    private String findCommonLine(Station origStation, Station destStation) {
        try {
            for (String origLine : origStation.getLineList()) {
                for (String destLine : destStation.getLineList()) {
                    if (origLine.equals(destLine)) return origLine;
                }
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getElapTime() {
        return elapTime;
    }

    public Station getDestStation() {
        return destStation;
    }

    public Station getOrigStation() {
        return origStation;
    }

    public String getLine() {
        return line;
    }

    public boolean getVisited() {
        return hasVisited;
    }

    public void setVisited() {
        hasVisited = true;
    }

    public void setUnvisited() {
        hasVisited = false;
    }

    @Override
    public String toString() {
        return (origStation.getStationName() + "-" + destStation.getStationName());
    }

    // 디버깅용
//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append(line);
//        sb.append("|");
//        sb.append(origStation.getStationName());
//        sb.append("-");
//        sb.append(destStation.getStationName());
//        sb.append("|");
//        sb.append(elapTime);
//        sb.append("\"");
//
//        return sb.toString();
//    }
}
