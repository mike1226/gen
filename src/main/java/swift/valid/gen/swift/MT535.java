package swift.valid.gen.swift;

import java.lang.Integer;

public class MT535 {


    /**
     * Creates an MT535 object with the given name and age.
     * 
     * @param name the name of the object
     * @param age the age of the object
     * @return 1 if the name length is more than 10 or the age is older than 18, 0 otherwise
     */
    public static int CreateMT535(String name, Integer age){

        // if name length is more than 10, return 1
        if(name.length() > 10){
            return 1;
        }
        // if age is old than 18, return 1
        if(age > 18){
            return 1;
        }

        return 0;

    }
    
}
