package com.amit.framework.qa.tests.api.API_Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cruisoring.throwables.FunctionThrowable;
import io.github.cruisoring.tuple.Tuple;
import jsonTuples.IJSONValue;
import jsonTuples.JSONObject;
import jsonTuples.Parser;
import jsonTuples.Utilities;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class TestJSONassert {

    @Test
    public void compareJson() throws JSONException {


        String actual = "{id:123,name:\"John\"}";
        String failureMessage = "Only one field is expected: name";
        try {
           // JSONAssert.assertEquals("{name:\"John\"}", actual, JSONCompareMode.STRICT);
            JSONAssert.assertEquals("{name:\"John\"}", actual, JSONCompareMode.LENIENT);

        } catch (AssertionError ae) {
            ae.printStackTrace();
        }
    }

    @Test
    public void compare2() throws IOException {

        String s1="{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"]\n" +
                "    }\n" +
                "}";
        String s2="{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 34,\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"] \n" +
                "    } \n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();
        JsonNode js1=mapper.readTree(s1);
        JsonNode js2=mapper.readTree(s2);
        System.out.println(js1);
        System.out.println(js2);
        assertEquals(js1,js2);

    }

    @Test
    public void testAsMutableObject_canBeUpdated() throws JsonProcessingException, ParseException {
        JSONObject obj = JSONObject.parse("{ \"age\": 123, \"other\": \"none\", \"name\": null, \"members\":[\"Alice\", \"Bob\"] }");
        System.out.println(obj);
        Map<String, Object> map = (Map<String, Object>)obj.asMutableObject();
        assertEquals(123, map.get("age"));
        assertEquals(null, map.get("name"));
        map.put("age", 23);
        map.remove("name");
        List members = (List)map.get("members");
        members.remove("Alice");
        members.add(0, "Alan");
        members.add("Carter");
        String json = new ObjectMapper().writeValueAsString(map);
        System.out.println(json);
//        JSONObject updated = Utilities.asJSONObject(map);
        assertEquals("{\"age\":23,\"other\":\"none\",\"members\":[\"Alan\",\"Bob\",\"Carter\"]}", json);
    }

    @Test
    public void testJsonify_withStudentInstances(){
        FunctionThrowable<Object, IJSONValue> studentToJSON = student -> Parser.parse(false,((Student)student).toJSON());
        Utilities.classConverters.put(Student.class, Tuple.create(studentToJSON, null));
        Map studentInClass = new HashMap(){{
            put("year", "Year7");
            put("active", true);
            put("students", Arrays.asList(
                    new Student("Alice", 12),
                    new Student("Bob", 13)
            ));
        }};
        System.out.println(Utilities.jsonify(studentInClass).toJSONString(null));
        assertEquals("{\"year\":\"Year7\",\"active\":true,\"students\":[{\"name\":\"Alice\",\"age\":12},{\"name\":\"Bob\",\"age\":13}]}",
                Utilities.jsonify(studentInClass).toJSONString(null));
        System.out.println("compare 2----------");
        assertEquals("{\"active\":true,\"year\":\"Year7\",\"students\":[{\"name\":\"Alice\",\"age\":12},{\"name\":\"Bob\",\"age\":13}]}",
                Utilities.jsonify(studentInClass).toJSONString(null));
    }
}
