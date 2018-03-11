package com.wavenowcam.dao;

import com.wavenowcam.dos.Beach;
import java.util.List;

/**
 *
 * @author guidocorazza
 */
public interface BeachDAO {
    
    public Long saveBeach(Beach beach);
    public void deleteBeach(Long id);
    public Beach getBeachById(Long id);
    public Beach getBeachByName(String name);
    public List<Beach> getAll();
    
}
