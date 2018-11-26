import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Functions {


    public static void main(String [] args) {
        Object[] check = assignTabling();

    }

    public static Object[] assignTabling() {
        Response resp = ReadExcelResponse.responseData();

        //only retain grade level and choices
        for (int i = 0; i < resp.responses.size(); i++) {
            resp.responses.get(i).remove(14);
            resp.responses.get(i).remove(5);
            resp.responses.get(i).remove(3);
            resp.responses.get(i).remove(1);
            resp.responses.get(i).remove(0);

        }
        resp.contentNames.remove(14);
        resp.contentNames.remove(5);
        resp.contentNames.remove(3);
        resp.contentNames.remove(1);
        resp.contentNames.remove(0);

        //sort by seniority
        //go find 1 then add its company
        resp.changeSenioritytoNumbers();

        resp.responses.sort((List<String> o1, List<String> o2) -> o2.get(1).compareTo(o1.get(1)));
        int[] counter = new int[resp.contentNames.size()];


        Map<String, String> round1, round2, round3;
        round1 = new HashMap<>();
        round2 = new HashMap<>();
        round3 = new HashMap<>();
        //for (int i = 2; i < resp.contentNames.size(); i++) {
        //    round1.put(resp.contentNames.get(i), new ArrayList<>());
        //    round2.put(resp.contentNames.get(i), new ArrayList<>());
        //    round3.put(resp.contentNames.get(i), new ArrayList<>());
        //}

        int limit = (resp.responses.size() / (resp.contentNames.size() - 2) - 1);

        //first round
        for (int i = 0; i < resp.responses.size(); i++) {
            outerloop:
            for (int j = 0; j < resp.responses.get(i).size(); j++) {
                if (resp.responses.get(i).get(j).equals("1.0") && counter[j] <= limit) {
                    round1.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                    counter[j] += 1;
                    resp.shiftAndDelete(i, 1);
                    break;

                } else if (resp.responses.get(i).get(j).equals("1.0") && counter[j] > limit) {
                    for (int k = 0; k < resp.responses.get(i).size(); k++) {
                        if (resp.responses.get(i).get(k).equals("2.0") && counter[j] <= limit) {
                            round1.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                            counter[j] += 1;
                            resp.shiftAndDelete(i, 2);
                            break outerloop;
                        } else if (resp.responses.get(i).get(k).equals("2.0") && counter[j] > limit) {
                            for (int l = 0; l < resp.responses.get(i).size(); l++) {
                                if (resp.responses.get(i).get(l).equals("3.0") && counter[j] <= limit) {
                                    round1.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                                    counter[j] += 1;
                                    resp.shiftAndDelete(i, 3);
                                    break outerloop;
                                } else if (resp.responses.get(i).get(l).equals("3.0") && counter[j] > limit) {
                                    for (int a = 0; a < resp.responses.get(i).size(); a++) {
                                        if (resp.responses.get(i).get(a).equals("4.0")) {
                                            round1.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                                            counter[j] += 1;
                                            resp.shiftAndDelete(i, 4);
                                            break outerloop;

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        counter = new int[resp.contentNames.size()];

        //second round
        for (int i = 0; i < resp.responses.size(); i++) {
            outerloop:
            for (int j = 0; j < resp.responses.get(i).size(); j++) {
                if (resp.responses.get(i).get(j).equals("1.0") && counter[j] <= limit) {
                    round2.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                    counter[j] += 1;
                    resp.shiftAndDelete(i, 1);
                    break;

                } else if (resp.responses.get(i).get(j).equals("1.0") && counter[j] > limit) {
                    for (int k = 0; k < resp.responses.get(i).size(); k++) {
                        if (resp.responses.get(i).get(k).equals("2.0") && counter[j] <= limit) {
                            round2.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                            counter[j] += 1;
                            resp.shiftAndDelete(i, 2);
                            break outerloop;
                        } else if (resp.responses.get(i).get(k).equals("2.0") && counter[j] > limit) {
                            for (int l = 0; l < resp.responses.get(i).size(); l++) {
                                if (resp.responses.get(i).get(l).equals("3.0") && counter[j] <= limit) {
                                    round2.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                                    counter[j] += 1;
                                    resp.shiftAndDelete(i, 3);
                                    break outerloop;
                                } else if (resp.responses.get(i).get(l).equals("3.0") && counter[j] > limit) {
                                    for (int a = 0; a < resp.responses.get(i).size(); a++) {
                                        if (resp.responses.get(i).get(a).equals("4.0")) {
                                            round2.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                                            counter[j] += 1;
                                            resp.shiftAndDelete(i, 4);
                                            break outerloop;

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        counter = new int[resp.contentNames.size()];

        //third round
        for (int i = 0; i < resp.responses.size(); i++) {
            outerloop:
            for (int j = 0; j < resp.responses.get(i).size(); j++) {
                if (resp.responses.get(i).get(j).equals("1.0") && counter[j] <= limit) {
                    round3.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                    counter[j] += 1;
                    resp.shiftAndDelete(i, 1);
                    break;

                } else if (resp.responses.get(i).get(j).equals("1.0") && counter[j] > limit) {
                    for (int k = 0; k < resp.responses.get(i).size(); k++) {
                        if (resp.responses.get(i).get(k).equals("2.0") && counter[j] <= limit) {
                            round3.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                            counter[j] += 1;
                            resp.shiftAndDelete(i, 2);
                            break outerloop;
                        } else if (resp.responses.get(i).get(k).equals("2.0") && counter[j] > limit) {
                            for (int l = 0; l < resp.responses.get(i).size(); l++) {
                                if (resp.responses.get(i).get(l).equals("3.0") && counter[j] <= limit) {
                                    round3.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                                    counter[j] += 1;
                                    resp.shiftAndDelete(i, 3);
                                    break outerloop;
                                } else if (resp.responses.get(i).get(l).equals("3.0") && counter[j] > limit) {
                                    for (int a = 0; a < resp.responses.get(i).size(); a++) {
                                        if (resp.responses.get(i).get(a).equals("4.0")) {
                                            round3.put(resp.responses.get(i).get(0), resp.contentNames.get(j));
                                            counter[j] += 1;
                                            resp.shiftAndDelete(i, 4);
                                            break outerloop;

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        Object[] rtn = new Object[4];
        rtn[0] = resp.contentNames;
        rtn[1] = round1;
        rtn[2] = round2;
        rtn[3] = round3;

        return rtn;

    }
}