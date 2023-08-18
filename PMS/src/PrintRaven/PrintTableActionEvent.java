package PrintRaven;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kkk
 */
public interface PrintTableActionEvent {

    public void onEdit(int row);

    public void onPrint(int row);

    public void onDelete(int row);
}
