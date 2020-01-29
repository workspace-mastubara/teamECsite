package com.internousdev.latte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.latte.dto.PurchaseHistoryInfoDTO;
import com.internousdev.latte.util.DBConnector;

public class PurchaseHistoryInfoDAO {
	/**
	 * 購入履歴情報テーブルに決済情報を挿入するためのメソッド
	 * @return 決済情報登録件数
	 */
	public int insertSettlementComplete(String userId, int productId, int productCount, int price, int destinationId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int  settle = 0;

		String sql = "INSERT INTO purchase_history_info "
				+ "(user_id, product_id, product_count, price, destination_id, regist_date, update_date) "
				+ "VALUES(?,?,?,?,?, now(),now())";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			ps.setInt(4, price);
			ps.setInt(5, destinationId);

			settle = ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		} try {
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return settle;
	}

	/**
	 * 商品購入履歴テーブルのユーザIDを元に、
	 * 宛先情報テーブル、商品情報テーブルを紐づけして
	 * 購入履歴リストをDTOListに格納するメソッド
	 * @return 購入履歴リスト
	 */
	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoList(String userId){
		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT "
				+ " phi.id,"										/* ID */
				+ " phi.user_id,"									/* ユーザーID */
				+ " phi.product_count,"								/* 個数 */
				+ " pi.product_id,"									/* 商品ID */
				+ " pi.product_name,"								/*商品名*/
				+ " pi.product_name_kana,"							/* 商品名かな */
				+ " pi.product_description,"						/* 商品詳細 */
				+ " pi.category_id,"								/* カテゴリID */
				+ " pi.image_file_name,"							/* 画像ファイルパス */
				+ " pi.image_file_path,"							/* 画像ファイル名 */
				+ " DATE_FORMAT(pi.release_date, '%Y/%m/%d') As release_date,"			/* 発売年月日 */
//				+ " FORMAT(pi.release_date, 'yyyy/MM/dd'),"			/* 発売年月日 */
				+ " pi.release_company,"							/* 画像ファイルパス */
				+ " phi.price,"										/* 値段 */
				+ " phi.price * phi.product_count AS totalprice," 	/* 合計金額 */
				+ " phi.regist_date,"								/* 登録日 */
				+ " di.family_name,"								/* 姓 */
				+ " di.first_name,"									/* 名 */
				+ " di.user_address "								/* 住所 */
				+ " FROM purchase_history_info AS phi"
				+ " LEFT JOIN product_info AS pi"
				+ " ON phi.product_id = pi.product_id"
				+ " LEFT JOIN destination_info AS di"
				+ " ON phi.destination_id = di.id"
				+ " WHERE phi.user_id = ?"
				+ " ORDER BY regist_date DESC";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				PurchaseHistoryInfoDTO purchaseHistoryInfoDTO = new PurchaseHistoryInfoDTO();
				purchaseHistoryInfoDTO.setProductName(rs.getString("product_name"));
				purchaseHistoryInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				purchaseHistoryInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				purchaseHistoryInfoDTO.setImageFileName(rs.getString("image_file_name"));
				purchaseHistoryInfoDTO.setReleaseCompany(rs.getString("release_company"));
				purchaseHistoryInfoDTO.setReleaseDate(rs.getString("release_date"));
				purchaseHistoryInfoDTO.setPrice(rs.getInt("price"));
				purchaseHistoryInfoDTO.setProductCount(rs.getInt("product_count"));
				purchaseHistoryInfoDTO.setFirstName(rs.getString("first_name"));
				purchaseHistoryInfoDTO.setFamilyName(rs.getString("family_name"));
				purchaseHistoryInfoDTO.setUserAddress(rs.getString("user_address"));
				purchaseHistoryInfoDTO.setTotalPrice(rs.getInt("totalprice"));

				purchaseHistoryInfoDTOList.add(purchaseHistoryInfoDTO);

			}
		} catch(SQLException e){
			e.printStackTrace();
		} try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}

		return purchaseHistoryInfoDTOList;
	}

	/**
	 * 購入履歴リストを全件削除するメソッド
	 * @return 削除件数(全消去で1件)
	 */
	public int deletePurchaseHistoryList(String userId){
		int deleteCount = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
//		WHEREのあとがuserIdになっていたのでuser_idになおしました　黒川
		String sql = "DELETE FROM purchase_history_info WHERE user_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			deleteCount = ps.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();
		} try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}

		return deleteCount;
	}

}
