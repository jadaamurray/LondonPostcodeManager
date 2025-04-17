package com.mycompany.londonpostcodemanager.shared;

public interface DeletablePostcodeManager extends PostcodeManagerInterface  {
    boolean delete(String postcode);
}
