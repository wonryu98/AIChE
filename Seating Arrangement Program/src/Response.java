import org.apache.poi.hssf.usermodel.HSSFCell;

import java.util.ArrayList;
import java.util.List;


public class Response {
    public List<String> contentNames;
    public List<List<String>> responses;

    //constructor
    public Response(List<List<HSSFCell>> resp) {
        contentNames = new ArrayList<String>();
        responses = new ArrayList<List<String>>();
        for (int k = 0; k < resp.size() - 1; k++) {
            responses.add(k, new ArrayList<String>());
        }
        for (int i = 0; i < resp.size(); i++) {
            for (int j = 0; j < resp.get(i).size(); j++) {
                if (i == 0) {
                    contentNames.add(resp.get(i).get(j).toString());
                } else {
                    responses.get(i - 1).add(resp.get(i).get(j).toString());
                }
            }
        }
    }


    public void changeSenioritytoNumbers() {
        //indexed at 1
        for (int i = 0; i < responses.size(); i++) {
            if (responses.get(i).get(1).toString().equals("Graduate Student")) {
                responses.get(i).set(1, "99");
            } else if (responses.get(i).get(1).toString().equals("Senior")) {
                responses.get(i).set(1, "98");
            } else if (responses.get(i).get(1).toString().equals("Junior")) {
                responses.get(i).set(1, "97");
            } else if (responses.get(i).get(1).toString().equals("Sophomore")) {
                responses.get(i).set(1, "96");
            } else if (responses.get(i).get(1).toString().equals("Freshman")) {
                responses.get(i).set(1, "95");
            }


        }

    }

    public void shiftAndDelete(int i, int deletedRating) {
        responses.get(i).set(responses.get(i).
                indexOf(Integer.toString(deletedRating) + ".0"), "0.0");


        for (int j = 1; j < responses.get(i).size(); j++) {
            if (j <= deletedRating) {
                continue;
            }
            if (responses.get(i).contains(Integer.toString(j) + ".0")) {
                int index = responses.get(i).indexOf(Integer.toString(j) + ".0");
                responses.get(i).set(index, Integer.toString(j - 1) + ".0");
            }
        }


    }


}
