/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Token.Token;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateful;

/**
 *
 * @author ahure
 */
@Local
public interface TokenService {
    
    public void addToken(String token);
    
}
