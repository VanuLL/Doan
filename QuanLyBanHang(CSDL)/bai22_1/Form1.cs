using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace bai22_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            HienThiSanPham();
            HienThiKhachHang();
            HienThiNhanVien();
            HienThiHoaDon();
            HienThiChiTietHoaDon();
        }

        private void Form1_Click(object sender, EventArgs e)
        {
            this.BackColor = Color.IndianRed;
        }
        SqlConnection conn = null;
        string strConn = "Server=VANCONG\\SQLEXPRESS;Database=DAQLBH;User Id=Manager;pwd=1";
        private void OpenConnection()
        {
            if (conn == null)
            {
                conn = new SqlConnection(strConn);
            }
            if (conn.State != ConnectionState.Open)
            {
                conn.Open();
            }
        }
        private void CloseConnection()
        {
            if (conn != null && conn.State == ConnectionState.Open)
                conn.Close();
        }

        private void HienThiSanPham()
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from HANGHOA";
                command.Connection = conn;
                SqlDataReader reader = command.ExecuteReader();
                lvSanPham.Items.Clear();
                while (reader.Read())
                {
                    String maHang = reader.GetString(0);
                    String tenHang = reader.GetString(1);
                    Decimal donGia = reader.GetDecimal(2);
                    int soLuong = reader.GetInt32(3);
                    ListViewItem lvi = new ListViewItem(maHang);
                    lvi.SubItems.Add(tenHang);
                    lvi.SubItems.Add(donGia + "");
                    lvi.SubItems.Add(soLuong + "");
                    lvSanPham.Items.Add(lvi);
                    lvi.Tag = maHang;
                }
                reader.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void HienThiChiTiet(String maHang)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from HANGHOA where mahang=@maHang";
                command.Connection = conn;
                SqlParameter parMa = new SqlParameter("@maHang", SqlDbType.VarChar);
                parMa.Value = maHang;
                command.Parameters.Add(parMa);
                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    String tenHang = reader.GetString(1);
                    Decimal donGia = reader.GetDecimal(2);
                    int soLuong = reader.GetInt32(3);
                    txtMaSanPham.Text = maHang + "";
                    txtTenSanPham.Text = tenHang;
                    txtDonGiaSanPham.Text = donGia + "";
                    txtSoLuongSanPham.Text = soLuong + "";
                }
                reader.Close();
            } catch(Exception e) {
                MessageBox.Show(e.Message);
            }
        }
        private void ThucThiXoa(String ma)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Delete from HangHoa where maHang=@maHang";
                command.Connection = conn;
                command.Parameters.Add("@maHang", SqlDbType.VarChar).Value = ma;
                int kq = command.ExecuteNonQuery();
                if(kq > 0)
                {
                    HienThiSanPham();
                    MessageBox.Show("Xóa thành công!");
                }
                else
                {
                    MessageBox.Show("Xóa thất bại!");
                }

            } catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void button5_Click_1(object sender, EventArgs e)
        {
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            command.CommandText = "Select *from HANGHOA where MAHANG=@ma";
            command.Connection = conn;
            SqlParameter param = new SqlParameter("@ma", SqlDbType.VarChar);
            param.Value = txtMaSanPham.Text;
            command.Parameters.Add(param);
            SqlDataReader reader = command.ExecuteReader();
            if (reader.Read())
            {
                txtMaSanPham.Text = reader.GetString(0);
                txtTenSanPham.Text = reader.GetString(1);
                txtDonGiaSanPham.Text = reader.GetDecimal(2) + "";
                txtSoLuongSanPham.Text = reader.GetInt32(3) + "";
            }
            reader.Close();
        }

        private void button6_Click_1(object sender, EventArgs e)
        {
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            string sql = "Update HANGHOA set TENHANG=@tenhang,DONGIA=@dongia,SOLUONG=soluong where MAHANG=@mahang";
            command.CommandText = sql;
            command.Connection = conn;

            command.Parameters.Add("@mahang", SqlDbType.VarChar).Value = txtMaSanPham.Text;
            command.Parameters.Add("@tenhang", SqlDbType.NVarChar).Value = txtTenSanPham.Text;
            command.Parameters.Add("@dongia", SqlDbType.Decimal).Value = txtDonGiaSanPham.Text;
            command.Parameters.Add("@soluong", SqlDbType.Int).Value = txtSoLuongSanPham.Text;
            int res = command.ExecuteNonQuery();
            if (res > 0)
            {
                HienThiSanPham();
                MessageBox.Show("Sửa thành công!");
            }
            else
            {
                MessageBox.Show("Sửa thất bại!");
            }
        }

        private void button7_Click_1(object sender, EventArgs e)
        {
            if (lvSanPham.SelectedItems.Count == 0)
            {
                MessageBox.Show("Chưa chọn khách hàng, không thể xóa!");
            }
            ListViewItem lvi = lvSanPham.SelectedItems[0];
            String ma = (String)lvi.Tag;
            DialogResult ret = MessageBox.Show("Bạn chắc chắn muốn xóa mã hàng này?", "Xác nhận?", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (ret == DialogResult.Yes)
            {
                ThucThiXoa(ma);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "INSERT INTO HANGHOA(MAHANG, TENHANG, DONGIA, SOLUONG) " + "values(@mahang, @tenhang, @dongia, @soluong)";
                command.CommandText = sql;
                command.Connection = conn;
                command.Parameters.Add("@mahang", SqlDbType.VarChar).Value = txtMaSanPham.Text;
                command.Parameters.Add("@tenhang", SqlDbType.NVarChar).Value = txtTenSanPham.Text;
                command.Parameters.Add("@dongia", SqlDbType.Decimal).Value = txtDonGiaSanPham.Text;
                command.Parameters.Add("@soluong", SqlDbType.Int).Value = txtSoLuongSanPham.Text;

                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    MessageBox.Show("Thêm thành công!");
                    HienThiSanPham();
                }
                else
                    MessageBox.Show("Thêm thất bại!");
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
        }

        private void lvSanPham_SelectedIndexChanged_1(object sender, EventArgs e)
        {
            if (lvSanPham.SelectedItems.Count == 0) return;
            ListViewItem lvi = lvSanPham.SelectedItems[0];
            String ma = (string)lvi.Tag;
            HienThiChiTiet(ma);
        }

        /// <summary>
        /// Khách hàng
        /// </summary>
        private void HienThiKhachHang()
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from KHACHHANG";
                command.Connection = conn;
                SqlDataReader reader = command.ExecuteReader();
                lvKhachHang.Items.Clear();
                while (reader.Read())
                {
                    String maKH = reader.GetString(0);
                    String tenKH = reader.GetString(1);
                    Decimal sdtKH = reader.GetDecimal(2);
                    String gioiTinhKH = reader.GetString(3);
                    String diaChiKH = reader.GetString(4);
                    ListViewItem lvi = new ListViewItem(maKH);
                    lvi.SubItems.Add(tenKH);
                    lvi.SubItems.Add(sdtKH + "");
                    lvi.SubItems.Add(gioiTinhKH + "");
                    lvi.SubItems.Add(diaChiKH + "");
                    lvKhachHang.Items.Add(lvi);
                    lvi.Tag = maKH;
                }
                reader.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void HienThiChiTiet1(String maKH)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from KHACHHANG where maKH=@maKH";
                command.Connection = conn;
                SqlParameter parMa = new SqlParameter("@maKH", SqlDbType.VarChar);
                parMa.Value = maKH;
                command.Parameters.Add(parMa);
                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    String tenKH = reader.GetString(1);
                    Decimal sdtKH = reader.GetDecimal(2);
                    String gioiTinhKH = reader.GetString(3);
                    String diaChiKH = reader.GetString(4);
                    txtMaKhachHang.Text = maKH;
                    txtTenKhachHang.Text = tenKH;
                    txtSDTKhachHang.Text = sdtKH + "";
                    txtGioiTinhKhachHang.Text = gioiTinhKH;
                    txtDiaChiKhachHang.Text = diaChiKH;
                }
                reader.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message);
            }
        }
        private void ThucThiXoa1(String maKH)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Delete from KHACHHANG where maKH=@maKH";
                command.Connection = conn;
                command.Parameters.Add("@maKH", SqlDbType.VarChar).Value = maKH;
                int kq = command.ExecuteNonQuery();
                if (kq > 0)
                {
                    HienThiKhachHang();
                    MessageBox.Show("Xóa thành công!");
                }
                else
                {
                    MessageBox.Show("Xóa thất bại!");
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "INSERT INTO KHACHHANG(MAKH, TENKH, SDT, GIOITINH, DIACHI) " + "values(@maKH, @tenKH, @sdtKH, @gioiTinhKH,@diaChiKH)";
                command.CommandText = sql;
                command.Connection = conn;
                command.Parameters.Add("@maKH", SqlDbType.VarChar).Value = txtMaKhachHang.Text;
                command.Parameters.Add("@tenKH", SqlDbType.NVarChar).Value = txtTenKhachHang.Text;
                command.Parameters.Add("@sdtKH", SqlDbType.Decimal).Value = txtSDTKhachHang.Text;
                command.Parameters.Add("@gioiTinhKH", SqlDbType.NVarChar).Value = txtGioiTinhKhachHang.Text;
                command.Parameters.Add("@diaChiKH", SqlDbType.VarChar).Value = txtDiaChiKhachHang.Text;
               
                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    MessageBox.Show("Thêm thành công!");
                    HienThiKhachHang();
                }
                else
                    MessageBox.Show("Thêm thất bại!");
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "Update KHACHHANG set TENKH=@tenKH,SDT=@sdtKH,GIOITINH=@gioiTinhKH where MAKH=@maKH";
                command.CommandText = sql;
                command.Connection = conn;

                command.Parameters.Add("@maKH", SqlDbType.VarChar).Value = txtMaKhachHang.Text;
                command.Parameters.Add("@tenKH", SqlDbType.NVarChar).Value = txtTenKhachHang.Text;
                command.Parameters.Add("@sdtKH", SqlDbType.Decimal).Value = txtSDTKhachHang.Text;
                command.Parameters.Add("@gioiTinhKH", SqlDbType.NVarChar).Value = txtGioiTinhKhachHang.Text;
                command.Parameters.Add("@diaChiKH", SqlDbType.VarChar).Value = txtDiaChiKhachHang.Text;
                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    HienThiKhachHang();
                    MessageBox.Show("Sửa thành công!");
                }
                else
                {
                    MessageBox.Show("Sửa thất bại!");
                }
            } catch (Exception ex) { MessageBox.Show(ex.ToString()); }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if (lvKhachHang.SelectedItems.Count == 0)
            {
                MessageBox.Show("Chưa chọn khách hàng, không thể xóa!");
            }
            ListViewItem lvi = lvKhachHang.SelectedItems[0];
            String ma = (String)lvi.Tag;
            DialogResult ret = MessageBox.Show("Bạn chắc chắn muốn xóa mã hàng này?", "Xác nhận?", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (ret == DialogResult.Yes)
            {
                ThucThiXoa1(ma);
            }
        }
        private void button9_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void lvKhachHang_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lvKhachHang.SelectedItems.Count == 0) return;
            ListViewItem lvi = lvKhachHang.SelectedItems[0];
            String ma = (string)lvi.Tag;
            HienThiChiTiet1(ma);
        }

        /// <summary>
        /// Hóa đơn
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        
        private void HienThiHoaDon()
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from HOADON";
                command.Connection = conn;
                SqlDataReader reader = command.ExecuteReader();
                lvHoaDon.Items.Clear();
                while (reader.Read())
                {
                    String maHoaDon = reader.GetString(0);
                    String maKH = reader.GetString(1);
                    String maNV = reader.GetString(2);
                    DateTime ngayLap = reader.GetDateTime(3);
                    ListViewItem lvi = new ListViewItem(maHoaDon);
                    lvi.SubItems.Add(maKH);
                    lvi.SubItems.Add(maNV);
                    lvi.SubItems.Add(ngayLap+"");
                    lvHoaDon.Items.Add(lvi);
                    lvi.Tag = maHoaDon;
                }
                reader.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void HienThiChiTietHoaDon()
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from CTHD";
                command.Connection = conn;
                SqlDataReader reader = command.ExecuteReader();
                lvChiTietHoaDon.Items.Clear();
                while (reader.Read())
                {
                    String maHoaDon = reader.GetString(0);
                    String maHang = reader.GetString(1);
                    int soLuong = reader.GetInt32(2);
                    ListViewItem lvi = new ListViewItem(maHoaDon);
                    lvi.SubItems.Add(maHang);
                    lvi.SubItems.Add(soLuong+"");
                    lvChiTietHoaDon.Items.Add(lvi);
                    lvi.Tag = maHoaDon + ";" + maHang + ";";
                }
                reader.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void ThucThiXoa2(String ma)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Delete from HoaDon where maHoadon=@mhd";
                command.Connection = conn;
                command.Parameters.Add("@mhd", SqlDbType.VarChar).Value = ma;
                int kq = command.ExecuteNonQuery();
                if (kq > 0)
                {
                    HienThiHoaDon();
                    MessageBox.Show("Xóa thành công!");
                }
                else
                {
                    MessageBox.Show("Xóa thất bại!");
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void button10_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "INSERT INTO HOADON(MAHOADON, MAKH, MANV, NGAYLAP) " + "values(@mhd, @mkh, @mnv, @mnl)";
                command.CommandText = sql;
                command.Connection = conn;
                command.Parameters.Add("@mhd", SqlDbType.VarChar).Value = txtMHD.Text;
                command.Parameters.Add("@mkh", SqlDbType.VarChar).Value = txtMKH.Text;
                command.Parameters.Add("@mnv", SqlDbType.VarChar).Value = txtMNV.Text;
                command.Parameters.Add("@mnl", SqlDbType.DateTime).Value = txtNL.Text;

                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    HienThiHoaDon();
                    MessageBox.Show("Thêm thành công!");
                }
                else {
                    MessageBox.Show("Thêm thất bại!");
                }
            }
            catch (Exception ex) { MessageBox.Show(ex.Message);}
        }

        private void button11_Click(object sender, EventArgs e)
        {
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            string sql = "Update HOADON set MAKH=@mkh, MANV=@mnv, NGAYLAP=@mnl where MAHOADON=@mhd";
            command.CommandText = sql;
            command.Connection = conn;

            command.Parameters.Add("@mhd", SqlDbType.VarChar).Value = txtMHD.Text;
            command.Parameters.Add("@mkh", SqlDbType.VarChar).Value = txtMKH.Text;
            command.Parameters.Add("@mnv", SqlDbType.VarChar).Value = txtMNV.Text;
            command.Parameters.Add("@mnl", SqlDbType.DateTime).Value = txtNL.Text;
            int res = command.ExecuteNonQuery();
            if (res > 0)
            {
                HienThiHoaDon();
                MessageBox.Show("Sửa thành công!");
            }
            else
            {
                MessageBox.Show("Sửa thất bại!");
            }
        }

        private void button12_Click(object sender, EventArgs e)
        {
            try
            {
                if (lvHoaDon.SelectedItems.Count == 0)
                {
                    MessageBox.Show("Chưa chọn khách hàng, không thể xóa!");
                }
                ListViewItem lvi = lvHoaDon.SelectedItems[0];
                String ma = (String)lvi.Tag;
                DialogResult ret = MessageBox.Show("Bạn chắc chắn muốn xóa mã hàng này?", "Xác nhận?", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (ret == DialogResult.Yes)
                {
                    ThucThiXoa2(ma);
                }
            } catch (Exception ex) { MessageBox.Show(ex.Message);}
        }

        private void lvHoaDon_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lvHoaDon.SelectedItems.Count == 0) return;
            ListViewItem lvi = lvHoaDon.SelectedItems[0];
            String ma = (string)lvi.Tag;
            HienThiChiTiet2(ma);
        }
        private void HienThiChiTiet2(String maHang)
        {
  //          try
    //        {
                String t = "";
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from HOADON where mahoadon=@maHang";
                command.Connection = conn;
                SqlParameter parMa = new SqlParameter("@maHang", SqlDbType.VarChar);
                parMa.Value = maHang;
                command.Parameters.Add(parMa);
                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    String mkh = reader.GetString(1);
                    String mnv = reader.GetString(2);
                    DateTime mnl = reader.GetDateTime(3);
                    txtMKH.Text = mkh + "";
                    txtMNV.Text = mnv;
                    txtNL.Text = mnl + "";
                    txtMHD.Text = maHang;
                    t = mkh;
                }
                reader.Close();
                OpenConnection();
                SqlCommand command1 = new SqlCommand();
                command1.CommandType = CommandType.Text;
                command1.CommandText = "Select * From KHACHHANG Where MAKH LIKE '"+t+"'";
                command1.Connection = conn;
                SqlDataReader reader1 = command1.ExecuteReader();
                if (reader1.Read())
                {
                    String a = reader1.GetString(1);
                    String b = reader1.GetDecimal(2)+"";
                    String c = reader1.GetString(3);
                    String d = reader1.GetString(4);
                    txtTKH.Text = a;
                    txtSDT.Text = b;
                    txtGT.Text = c;
                    txtDC.Text = d;
                }
                reader1.Close();
     //       }
      //      catch (Exception e)
        //    {
        //        MessageBox.Show(e.Message);
          //  }
        }

        private void lvChiTietHoaDon_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lvChiTietHoaDon.SelectedItems.Count == 0) return;
            ListViewItem lvi = lvChiTietHoaDon.SelectedItems[0];
            String s = (string)lvi.Tag;
            String [] tem = s.Split(';');
            HienThiChiTiet3(tem[0], tem[1]);
        }
        private void HienThiChiTiet3(String maHD, String maHang)
        {
            try
            {
                String mHDClone = "";
                int sClone = 0;
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from CTHD where mahoadon=@maHD";
                command.Connection = conn;
                SqlParameter parMa = new SqlParameter("@maHD", SqlDbType.VarChar);
                parMa.Value = maHD;
                command.Parameters.Add(parMa);
                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    String mh = reader.GetString(1);
                    int msl = reader.GetInt32(2);
                    txtMHD.Text = maHD;
                    txtMH.Text = mh;
                    txtSL.Text = msl+"";
                    sClone = msl;
                    mHDClone = reader.GetString(0);
                }
                reader.Close();
                OpenConnection();
                SqlCommand command2 = new SqlCommand();
                command2.CommandType = CommandType.Text;
                command2.CommandText = "Select * from HANGHOA where MaHang Like '" + maHang +"'";
                command2.Connection = conn;
                SqlDataReader reader2 = command2.ExecuteReader();
                if (reader2.Read())
                {
                    Decimal d = reader2.GetDecimal(2);
                    txtDGCT.Text = d*sClone+"";
                }
                reader2.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message);
            }
        }

        private void button18_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "INSERT INTO CTHD(MAHOADON, MAHANG, SOLUONG) " + "values(@mhd, @mh, @msl)";
                command.CommandText = sql;
                command.Connection = conn;
                command.Parameters.Add("@mhd", SqlDbType.VarChar).Value = txtMHD.Text;
                command.Parameters.Add("@mh", SqlDbType.VarChar).Value = txtMH.Text;
                command.Parameters.Add("@msl", SqlDbType.Int).Value = txtSL.Text;

                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    HienThiChiTietHoaDon();
                    MessageBox.Show("Thêm thành công!");
                }
                else
                {
                    MessageBox.Show("Thêm thất bại!");
                }
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
        }

        private void button17_Click(object sender, EventArgs e)
        {
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            string sql = "Update CTHD set MAHOADON=@mhd, MAHANG=@mh, SOLUONG=@sl where MAHOADON=@mhd";
            command.CommandText = sql;
            command.Connection = conn;

            command.Parameters.Add("@mhd", SqlDbType.VarChar).Value = txtMHD.Text;
            command.Parameters.Add("@mh", SqlDbType.VarChar).Value = txtMH.Text;
            command.Parameters.Add("@sl", SqlDbType.Int).Value = txtSL.Text;
            int res = command.ExecuteNonQuery();
            if (res > 0)
            {
                HienThiChiTietHoaDon();
                MessageBox.Show("Sửa thành công!");
            }
            else
            {
                MessageBox.Show("Sửa thất bại!");
            }
        }

        private void button16_Click(object sender, EventArgs e)
        {
            try
            {
                if (lvChiTietHoaDon.SelectedItems.Count == 0)
                {
                    MessageBox.Show("Chưa chọn khách hàng, không thể xóa!");
                }
                ListViewItem lvi = lvChiTietHoaDon.SelectedItems[0];
                String ma = (String)lvi.Tag;
                DialogResult ret = MessageBox.Show("Bạn chắc chắn muốn xóa mã hàng này?", "Xác nhận?", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (ret == DialogResult.Yes)
                {
                    ThucThiXoa3(ma);
                }
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
        }
        public void ThucThiXoa3(String ma)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Delete from CTHD where maHOADON=@maHang";
                command.Connection = conn;
                command.Parameters.Add("@maHang", SqlDbType.VarChar).Value = ma;
                int kq = command.ExecuteNonQuery();
                if (kq > 0)
                {
                    HienThiChiTietHoaDon();
                    MessageBox.Show("Xóa thành công!");
                }
                else
                {
                    MessageBox.Show("Xóa thất bại!");
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        /// <summary>
        /// Nhân viên
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>

        private void button22_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "INSERT INTO NHANVIEN(MANV, TENNV, SDT, GIOITINH, DIACHI) " + "values(@maNV, @tenNV, @sdtNV, @gioiTinhNV, @diaChiNV)";
                command.CommandText = sql;
                command.Connection = conn;
                command.Parameters.Add("@maNV", SqlDbType.VarChar).Value = txtMaNhanVien.Text;
                command.Parameters.Add("@tenNV", SqlDbType.NVarChar).Value = txtTenNhanVien.Text;
                command.Parameters.Add("@sdtNV", SqlDbType.Decimal).Value = txtSDTNhanVien.Text;
                command.Parameters.Add("@gioiTinhNV", SqlDbType.NVarChar).Value = txtGioiTinhNhanVien.Text;
                command.Parameters.Add("@diaChiNV", SqlDbType.VarChar).Value = txtDiaChiNhanVien.Text;

                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    MessageBox.Show("Thêm thành công!");
                    HienThiNhanVien();
                }
                else
                    MessageBox.Show("Thêm thất bại!");
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
        }

        private void lvNhanVien_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lvNhanVien.SelectedItems.Count == 0) return;
            ListViewItem lvi = lvNhanVien.SelectedItems[0];
            String ma = (string)lvi.Tag;
            HienThiChiTiet4(ma);
        }
        private void HienThiChiTiet4(String maNV)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from NHANVIEN where maNV=@maNV";
                command.Connection = conn;
                SqlParameter parMa = new SqlParameter("@maNV", SqlDbType.VarChar);
                parMa.Value = maNV;
                command.Parameters.Add(parMa);
                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    String tenNV = reader.GetString(1);
                    Decimal sdtNV = reader.GetDecimal(2);
                    String gioiTinhNV = reader.GetString(3);
                    String diaChiNV = reader.GetString(4);
                    txtMaNhanVien.Text = maNV;
                    txtTenNhanVien.Text = tenNV;
                    txtSDTNhanVien.Text = sdtNV + "";
                    txtGioiTinhNhanVien.Text = gioiTinhNV;
                    txtDiaChiNhanVien.Text = diaChiNV;
                }
                reader.Close();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message);
            }
        }
        private void HienThiNhanVien()
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from NHANVIEN";
                command.Connection = conn;
                SqlDataReader reader = command.ExecuteReader();
                lvNhanVien.Items.Clear();
                while (reader.Read())
                {
                    String maNV = reader.GetString(0);
                    String tenNV = reader.GetString(1);
                    Decimal sdtNV = reader.GetDecimal(2);
                    String gioiTinhNV = reader.GetString(3);
                    String diaChiNV = reader.GetString(4);
                    ListViewItem lvi = new ListViewItem(maNV);
                    lvi.SubItems.Add(tenNV);
                    lvi.SubItems.Add(sdtNV + "");
                    lvi.SubItems.Add(gioiTinhNV);
                    lvi.SubItems.Add(diaChiNV);
                    lvNhanVien.Items.Add(lvi);
                    lvi.Tag = maNV;
                }
                reader.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void button21_Click(object sender, EventArgs e)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                string sql = "Update NHANVIEN set TENNV=@tenNV,SDT=@sdtNV,GIOITINH=@gioiTinhNV where MANV=@maNV";
                command.CommandText = sql;
                command.Connection = conn;

                command.Parameters.Add("@maNV", SqlDbType.VarChar).Value = txtMaNhanVien.Text;
                command.Parameters.Add("@tenNV", SqlDbType.NVarChar).Value = txtTenNhanVien.Text;
                command.Parameters.Add("@sdtNV", SqlDbType.Decimal).Value = txtSDTNhanVien.Text;
                command.Parameters.Add("@gioiTinhNV", SqlDbType.NVarChar).Value = txtGioiTinhNhanVien.Text;
                command.Parameters.Add("diaChiNV", SqlDbType.VarChar).Value = txtDiaChiNhanVien.Text;
                int res = command.ExecuteNonQuery();
                if (res > 0)
                {
                    HienThiNhanVien();
                    MessageBox.Show("Sửa thành công!");
                }
                else
                {
                    MessageBox.Show("Sửa thất bại!");
                }
            }
            catch (Exception ex) { MessageBox.Show(ex.ToString()); }
        }

        private void button20_Click(object sender, EventArgs e)
        {
            if (lvNhanVien.SelectedItems.Count == 0)
            {
                MessageBox.Show("Chưa chọn nhân viên, không thể xóa!");
            }
            ListViewItem lvi = lvNhanVien.SelectedItems[0];
            String ma = (String)lvi.Tag;
            DialogResult ret = MessageBox.Show("Bạn chắc chắn muốn xóa mã này?", "Xác nhận?", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (ret == DialogResult.Yes)
            {
                ThucThiXoa4(ma);
            }
        }
        private void ThucThiXoa4(String ma) {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Delete from NHANVIEN where maNV=@maNV";
                command.Connection = conn;
                command.Parameters.Add("@maNV", SqlDbType.VarChar).Value = ma;
                int kq = command.ExecuteNonQuery();
                if (kq > 0)
                {
                    HienThiNhanVien();
                    MessageBox.Show("Xóa thành công!");
                }
                else
                {
                    MessageBox.Show("Xóa thất bại!");
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void button13_Click(object sender, EventArgs e)
        {
            txtMKH.Text = "";
            txtMHD.Text = "";
            txtMNV.Text = "";
            txtDC.Text = "";
            txtGT.Text = "";
            txtTKH.Text = "";
            txtSDT.Text = "";
        }

        private void button14_Click(object sender, EventArgs e)
        {
            txtDGCT.Text = "";
            txtMH.Text = "";
            txtSL.Text = "";
        }

        private void button23_Click(object sender, EventArgs e)
        {
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            command.CommandText = "Select *from KHACHHANG where MAKH=@ma";
            command.Connection = conn;
            SqlParameter param = new SqlParameter("@ma", SqlDbType.VarChar);
            param.Value = txtMaKhachHang.Text;
            command.Parameters.Add(param);
            SqlDataReader reader = command.ExecuteReader();
            if (reader.Read())
            {
                txtMaKhachHang.Text = reader.GetString(0);
                txtTenKhachHang.Text = reader.GetString(1);
                txtSDTKhachHang.Text = reader.GetDecimal(2) + "";
                txtGioiTinhKhachHang.Text = reader.GetString(3) + "";
                txtDiaChiKhachHang.Text = reader.GetString(4);
            }
            reader.Close();
        }

        private void button24_Click(object sender, EventArgs e)
        {
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            command.CommandText = "Select *from NHANVIEN where MANV=@ma";
            command.Connection = conn;
            SqlParameter param = new SqlParameter("@ma", SqlDbType.VarChar);
            param.Value = txtMaNhanVien.Text;
            command.Parameters.Add(param);
            SqlDataReader reader = command.ExecuteReader();
            if (reader.Read())
            {
                txtMaNhanVien.Text = reader.GetString(0);
                txtTenNhanVien.Text = reader.GetString(1);
                txtSDTNhanVien.Text = reader.GetDecimal(2) + "";
                txtGioiTinhNhanVien.Text = reader.GetString(3);
                txtDiaChiNhanVien.Text = reader.GetString(4);
            }
            reader.Close();
        }

        private void button25_Click(object sender, EventArgs e)
        {
            String tempt = "";
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            command.CommandText = "Select *from HOADON where MAHOADON=@ma";
            command.Connection = conn;
            SqlParameter param = new SqlParameter("@ma", SqlDbType.VarChar);
            param.Value = txtMHD.Text;
            command.Parameters.Add(param);
            SqlDataReader reader = command.ExecuteReader();
            if (reader.Read())
            {
                txtMHD.Text = reader.GetString(0);
                txtMKH.Text = reader.GetString(1);
                txtMNV.Text = reader.GetString(2);
                txtNL.Text = reader.GetDateTime(3) + "";
                tempt = reader.GetString(0);    
            }
            reader.Close();
           HienThiChiTiet2(tempt);
        }

        private void button26_Click(object sender, EventArgs e)
        {
            String tempt = "";
            OpenConnection();
            SqlCommand command = new SqlCommand();
            command.CommandType = CommandType.Text;
            command.CommandText = "Select *from CTHD where MAHOADON=@ma";
            command.Connection = conn;
            SqlParameter param = new SqlParameter("@ma", SqlDbType.VarChar);
            param.Value = txtMHD.Text;
            command.Parameters.Add(param);
            SqlDataReader reader = command.ExecuteReader();
            if (reader.Read())
            {
                txtMH.Text = reader.GetString(1);
                txtSL.Text = reader.GetInt32(2)+"";
                tempt = reader.GetString(0);
            }
            reader.Close();
            HienThiTim(tempt);
        }
        private void HienThiTim(String a)
        {
            try
            {
                OpenConnection();
                SqlCommand command = new SqlCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = "Select * from CTHD where mahoadon Like '" + a + "'";
                command.Connection = conn;
                SqlDataReader reader = command.ExecuteReader();
                lvChiTietHoaDon.Items.Clear();
                while (reader.Read())
                {
                    String maHoaDon = reader.GetString(0);
                    String maHang = reader.GetString(1);
                    int soLuong = reader.GetInt32(2);
                    ListViewItem lvi = new ListViewItem(maHoaDon);
                    lvi.SubItems.Add(maHang);
                    lvi.SubItems.Add(soLuong + "");
                    lvChiTietHoaDon.Items.Add(lvi);
                    lvi.Tag = maHoaDon + ";" + maHang + ";";

                }
                reader.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void button15_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            Close();
        }

        private void button19_Click(object sender, EventArgs e)
        {
            Close();
            conn.Close();
        }
    }
}