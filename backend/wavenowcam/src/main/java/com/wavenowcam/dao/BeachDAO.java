package com.wavenowcam.dao;

import com.wavenowcam.dos.Beach;
import java.util.List;

/**
 *
 * @author guidocorazza
 */
public interface BeachDAO {
    
    Long saveBeach(Beach beach);
    void deleteBeach(Long id);
    Beach getBeachById(Long id);
    Beach getBeachByName(String name);
    List<Beach> getAll();
    
}
