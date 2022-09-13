package persistence;

import org.json.JSONObject;

// referenced and modified from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns as JSON object
    JSONObject toJson();
}
