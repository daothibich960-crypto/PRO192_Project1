
package Enum;


public enum Position {
    MANAGER(2.5),          // Quản lý cửa hàng
    TEA_MASTER(2.2),       // Nghệ nhân pha trà: chuyên gia về trà , am hiểu sâu về trà
    TEA_LADY(1.7),      // Trà nương: là người thực hiện nghi lễ pha và phục vụ trà
    TEA_ASSISTANT(1.2), // Trà nô : là người hỗ trợ cho tea Master
    SALES_STAFF(1.5),      // Nhân viên bán hàng
    CASHIER(1.5),          // Thu ngân
    WAREHOUSE_STAFF(1.4) ;  // Nhân viên kho// Nhân viên kho
    
    private final double heSoLuong ;

    private Position(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }
    public double getHeSoLuong(){
        return heSoLuong;
    }
    

    
    
}
