/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class DBException extends Exception
{
    public DBException(String message)
    {
        super(message);
    }
    public DBException(Exception e)
    {
        super(e);
    }
}
