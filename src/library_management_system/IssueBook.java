/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package library_management_system;

/**
 *
 * @author Rajat
 */
import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
//to fetch book details from bookdetails table and display it to book detail panel
        public boolean getBookDetails(){
            boolean isValid = false;
            int bookId = Integer.parseInt(txt_bookId.getText());
            
            try {
                
                Connection con = Database_Connection.getConnection();
                PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
                pst.setInt(1,bookId);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                    lbl_bookid.setText(rs.getString("book_id"));
                    lbl_bookname.setText(rs.getString("book_name"));
                    lbl_author.setText(rs.getString("author"));
                    lbl_quantity.setText(rs.getString("quantity"));
                    lbl_bookError.setText("");
                    isValid = true;
                }
                else{
                    lbl_bookError.setText("Invalid Book Id!!!");
                    isValid = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isValid;
        }
        
        //to fetch student details from studentdetails table and display it to student detail panel
        public boolean getStudentDetails(){
            boolean isValid = false;
            int studentId = Integer.parseInt(txt_studentId.getText());
            
            try {
                
                Connection con = Database_Connection.getConnection();
                PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
                pst.setInt(1,studentId);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                    lbl_studentid.setText(rs.getString("student_id"));
                    lbl_studentname.setText(rs.getString("name"));
                    lbl_course.setText(rs.getString("course"));
                    lbl_branch.setText(rs.getString("branch"));
                    lbl_sem.setText(rs.getString("sem"));
                    lbl_studentError.setText("");
                    isValid = true;
                }else
                {
                    lbl_studentError.setText("Invalid Student Id!!!");
                    isValid = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
           return isValid;
        }

        
         // insert issue book details to database
            
            public boolean issueBook(){
                boolean isIssued = false;
                int bookId = Integer.parseInt(txt_bookId.getText());
                int studentId = Integer.parseInt(txt_studentId.getText());
                String bookName = lbl_bookname.getText();
                String studentName = lbl_studentname.getText();
                
                Date uIssueDate = date_issueDate.getDatoFecha();
                Date uDueDate = date_dueDate.getDatoFecha();
                
                Long l1 = uIssueDate.getTime();
                Long l2 = uDueDate.getTime();
                java.sql.Date sIssueDate = new java.sql.Date(l1);
                java.sql.Date sDueDate = new java.sql.Date(l2);
                
                try {
                    Connection con = Database_Connection.getConnection();
                    String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1,bookId);
                    pst.setString(2, bookName);
                    pst.setInt(3, studentId);
                    pst.setString(4, studentName);
                    pst.setDate(5, sIssueDate);
                    pst.setDate(6, sDueDate);
                    pst.setString(7, "pending");
                    
                    int rowCount = pst.executeUpdate();
                    
                    if(rowCount > 0){
                        isIssued = true;
                    }else{
                        isIssued = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 
                return isIssued;
            }
            
         //update book count
            public void updateBookCount(){
               int bookId = Integer.parseInt(txt_bookId.getText());
               
                try {
                    Connection con = Database_Connection.getConnection();
                    String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1,bookId);
                    int rowCount = pst.executeUpdate();
                    if(rowCount > 0){
                        JOptionPane.showMessageDialog(this, "Book count updated");
                        int initialCount = Integer.parseInt(lbl_quantity.getText());
                        lbl_quantity.setText(Integer.toString(initialCount - 1));
                    }else{
                         JOptionPane.showMessageDialog(this, "Can,t update book count");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
            
         // checking weather, book already allocated or not
            
            public boolean isAlreadyIssued(){
                boolean isAlreadyIssued = false;
                
                 int bookId = Integer.parseInt(txt_bookId.getText());
                int studentId = Integer.parseInt(txt_studentId.getText());
               
                try {
                    Connection con = Database_Connection.getConnection();
                    String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
                     PreparedStatement pst = con.prepareStatement(sql);
                     pst.setInt(1, bookId);
                     pst.setInt(2, studentId);
                     pst.setString(3, "pending");
                     ResultSet rs = pst.executeQuery();
                     
                     if(rs.next())
                     {
                         isAlreadyIssued = true;
                     }else{
                         isAlreadyIssued = false;
                     }
                     
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return isAlreadyIssued;
            }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_sem = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rSDateChooser2 = new rojeru_san.componentes.RSDateChooser();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel19 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 300, 5));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Branch :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        lbl_branch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 210, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student Name :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Student Id :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        lbl_studentid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 210, 30));

        lbl_studentname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 210, 30));

        lbl_course.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 210, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Semseter :");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        lbl_sem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_sem.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_sem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, 210, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel5.setText("Student Details");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, 100));

        lbl_studentError.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 210, 30));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 390, 820));

        jPanel3.setBackground(new java.awt.Color(255, 102, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel12.setText("Book Details");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, 100));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 300, 5));

        lbl_quantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 240, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book Id :");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        lbl_bookid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 240, 30));

        lbl_bookname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 290, 30));

        lbl_author.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 240, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Quantity :");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        lbl_bookError.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 255, 0));
        jPanel3.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 200, 30));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 820));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel2.setText("Issue Book");
        panel_main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 90, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        panel_main.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, 290, 5));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        panel_main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Book Id :");
        panel_main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 140, 30));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 0)));
        txt_bookId.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id...");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 280, 220, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Issue Date :");
        panel_main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 140, 30));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 0)));
        txt_studentId.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student Id...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 360, 220, -1));

        date_issueDate.setColorBackground(new java.awt.Color(255, 102, 0));
        date_issueDate.setColorDiaActual(new java.awt.Color(255, 51, 0));
        date_issueDate.setColorForeground(new java.awt.Color(255, 102, 0));
        date_issueDate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        date_issueDate.setPlaceholder("Select Issue Date");
        panel_main.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 440, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Student Id :");
        panel_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 360, 140, 30));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 102, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Issue Date :");
        panel_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 140, 30));
        panel_main.add(rSDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 440, -1, -1));

        date_dueDate.setColorBackground(new java.awt.Color(255, 102, 0));
        date_dueDate.setColorDiaActual(new java.awt.Color(255, 51, 0));
        date_dueDate.setColorForeground(new java.awt.Color(255, 102, 0));
        date_dueDate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        date_dueDate.setPlaceholder("Select Due Date");
        panel_main.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 520, -1, -1));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 102, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Due Date :");
        panel_main.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 520, 140, 30));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(51, 51, 51));
        rSMaterialButtonCircle1.setText("ISSUE BOOK");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 610, 280, 70));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 820));

        setSize(new java.awt.Dimension(1290, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
         HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if(!txt_bookId.getText().equals("")){
            
            getBookDetails();
        }       
        
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
         if(!txt_studentId.getText().equals("")){
             
            getStudentDetails();
        }       
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        
        if(lbl_quantity.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this, "This book is not available");
        }else{
            
             if(isAlreadyIssued() == false){
            if(issueBook() == true && getStudentDetails() == true && getBookDetails() == true)
        {
            JOptionPane.showMessageDialog(this, "Book Issused Scuccefully");
            updateBookCount();
        }else{
            JOptionPane.showMessageDialog(this, "Can't Issuse the Book");
        }
        }else{
            JOptionPane.showMessageDialog(this, "This student already has this book");
        }
       
        }
          
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_sem;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_studentname;
    private javax.swing.JPanel panel_main;
    private rojeru_san.componentes.RSDateChooser rSDateChooser2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
