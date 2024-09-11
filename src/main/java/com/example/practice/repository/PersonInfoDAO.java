package com.example.practice.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.practice.entity.PersonInfo;
import com.example.practice.vo.JoinVo;

@Repository
public interface PersonInfoDAO extends JpaRepository<PersonInfo, String> {

	public List<PersonInfo> findByAgeGreaterThanEqualOrderByAgeDesc(int age);

	// OrderBy: 排序-Asc 小到大(預設) Desc 大到小)
	// OrderBy: 將撈取之資料(包含篩選後)，根據指定欄位做排序，故JPA語法排"最後"
	public List<PersonInfo> findByAgeLessThanEqualOrderByAge(int age);

	// 即使篩選條件相同，每個LessThan/GreaterThan前都須加上各自的變數名稱
	public List<PersonInfo> findByAgeLessThanEqualOrAgeGreaterThanEqual(int age1, int age2);

	// Between: 篩選範圍是age1 <= x <= age2
	// 限制回傳資料筆數: First/Top + 回傳筆數(語法位置寫在find之後)
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int age1, int age2);

	// Containing: 關鍵字搜尋篩選，語法位置寫在變數名稱之後，無法分割關鍵字，多重字串搜尋將會是為一體
	public List<PersonInfo> findByCityContaining(String keyword);

	// Containing可用於多欄位搜尋篩選(Or/And)
	public List<PersonInfo> findByCityContainingOrNameContaining(String keywordCity, String keywordName);

	// Like： 完整搜尋，篩選資料條件為city == keyword
	public List<PersonInfo> findByCityLike(String keyword);

	public List<PersonInfo> findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(int age, String keyword);

	// In: 精準搜尋，適用於同欄位且多個不同關鍵字之搜尋篩選，ex: 可同時篩選搜尋"屏東"、"高雄"...
	public List<PersonInfo> findByCityIn(List<String> keywordList);

	
	
//	----------------------------------------------------------------
	// JPQL
	// JPQL，nativeQuery預設是false
	// nativeQuery = false時，語法中的表和欄位名稱各是操作Entity class名稱和屬性變數名稱
	// nativeQuery = true時，語法中的表和欄位名稱是使用資料庫中資料表(@Table後面的名字)和欄位(@Column中的名字)的名稱
	
	// ****insert 只能使用於nativeQuery = true****
	
	// 方法的回傳型態用int接，可用來表示新增成功資料的筆數，0代表沒有新增任何資料
	// values()中的?數字，指的是方法insert()中參數的位置
	// JPQL: insert/update/delete要求要加上@Transactional和@Modifying
	// @Transactional import的library，javax 和springframework都可以使用兩者差異可參照PPT spring boot_02 @Transactional部分

	
	// insert: 只能新增資訊，即為要新增之資訊的PK不能存在於DB，若已存在會報錯
	@Modifying
	@Transactional
	@Query(value = "insert into person_info (id, name, age, city) values (?1, ?2, ?3, ?4)", //
			nativeQuery = true)
	public int insert(String inputId, String InputName, int InputAge, String InputCity);
	
	@Modifying
	@Transactional
	@Query(value = "insert into person_info (id, name, age, city) values"
			+ " (:inputId, :inputName, :inputAge, :inputCity)", nativeQuery = true)
	public int insert2( //
			@Param("inputId") String inputId, //
			@Param("inputName") String InputName, //
			@Param("inputAge") int InputAge, //
			@Param("inputCity") String InputCity);

	
	
//	----------------------------------------------------------------
	// 回傳型態int表示資料更新成功比數，0表示沒有任何資料更新成功
	// where~一定要加上，否則會更新所有資料
	// JPQL: insert/update/delete要求要加上@Transactional和@Modifying
	
	
	// update
	@Modifying
	@Transactional
	@Query(value = "update person_info set city = ?1 where id = ?2", nativeQuery = true)
	public int updateCityById(String city, String id);
	
	// nativeQuery = false時，語法中的表和欄位名稱各是操作Entity class名稱和屬性變數名稱
	// nativeQuery = false是預設，所以可以不用寫
	@Modifying
	@Transactional
	@Query(value = "update PersonInfo set city = ?1 where id = ?2", nativeQuery = false)
	public int updateCityById2(String city, String id);

	// P.name = case when ?2 is null then P.name else ?2
	// 先從等號右邊: 若?2的值是null時，把P.name的值(原始值)設定給欄位name
	// else ?2: 否則將?2(外部值)的值設定給欄位name
	@Modifying
	@Transactional
	@Query(value = "update PersonInfo as P set "
			+ " P.name = case when ?2 is null then P.name else ?2 end, "
			+ " P.age = case when ?3 <=0 then P.age else ?3 end, "
			+ " P.city = case when ?4 is null then P.city else ?4 end where id = ?1", //
	nativeQuery = false)
	public int updateInfo(String id, String name, int age, String city);
	
	// coalesce只會判斷null
	// 可以有多個參數，會從value1開始找，並返回#第一個非null#的參數值，若全部參數為null則回傳null
	// 數值的判斷一樣用case when
	@Modifying
	@Transactional
	@Query(value = "update PersonInfo as P set "
			+ " P.name = coalesce(?2, P.name), "
			+ " P.age = case when ?3 <=0 then P.age else ?3 end, "
			+ " P.city = coalesce(?4, P.city) where id = ?1", //
	nativeQuery = false)
	public int updateInfo2(String id, String name, int age, String city);
	
	
	
//	----------------------------------------------------------------
	// JPQL: insert/update/delete要求要加上@Transactional和@Modifying
	
	
	// delete
	@Modifying
	@Transactional
	@Query(value = "delete from person_info where id = ?1", nativeQuery = true)
	public int deleteById2(String id);
	

	
//	----------------------------------------------------------------
	// select
	// *代表所有欄位，只能用於nativeQuery = true
	@Query(value = "select * from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll();
	
	// 等於*
	@Query(value = "select id, name, age, city from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll2();
	
	// 查詢部分欄位，只能使用在nativeQuery = false
	// select後面要撈取的資料欄位必須要用#建構方法#的寫法: new類別名稱(屬性名稱)
	// new PersonInfo(id, name, age, city): 這個建構方法一定得要存在，才能在語法中使用
	// 就是看語法中會用到撈幾個參數，其對應的建構方法就得要存在
	// 未被撈取的欄位，會賦予預設值，例如city未在撈取的欄位中，所以結果中的city全部都會是預設值null
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo")
	public List<PersonInfo> selectAll21();
	
	// 使用別名(Alias): as可以省略，P的名稱任意命名，用來代表整個Entity
	// P等同於使用所有欄位的建構方法，new PersonInfo(id, name, age, city)
	@Query(value = "select P from PersonInfo as P")
	public List<PersonInfo> selectAll22();
	
	// distinct: 用來篩選重複的欄位資料值，等同於Collection中的Set，相同值只保留一個
	// 若distinct後面的欄位有多個，表示篩選這些欄位串起來的值
	@Query(value = "select distinct city from PersonInfo")
	public List<String> selectDistinct();
	
	@Query(value = "select distinct new PersonInfo(name, city) from PersonInfo")
	public List<PersonInfo> selectDistinct2();
	
	// 以下2種是使用nativeQuery = true時的寫法，但最後要取值時都沒有nativeQuery = false時來的簡單
	// 所以建議使用nativeQuery = false的寫法
	@Query(value = "select distinct name, city from person_info", nativeQuery = true)
	public List<Map<String, String>> selectDistinct3();
	
	@Query(value = "select distinct name, city from person_info", nativeQuery = true)
	public List<Object> selectDistinct4();
	
	// 查詢部分欄位: 以下方法會報錯，nativeQuery = true需寫出完整欄位
	@Query(value = "select id, name, age from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll3();
	
	// 附條件查詢部分欄位
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where age > ?1")
	public List<PersonInfo> selectAll31(int age);


	// 附條件查詢部分欄位
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where age != ?1")
	public List<PersonInfo> selectAll32(int age);

	// 附條件查詢部分欄位
	// 下方SQL語句中，執行順序為: 1. from  2. where  3. select
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where city != ?1")
	public List<PersonInfo> selectAll33(String id);
	
	// 不等於: <> / !=
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where city <> ?1")
	public List<PersonInfo> selectAll34(String id);
	
	// order by 欄位名稱 排序方法( ASC | DESC ): 排序方法預設為ASC，可略不寫
	// 若排序欄位的欄位有相同值時，會再依照PK排序(ASC)
	@Query(value = "select P from PersonInfo as P where age > ?1 order by age asc")
	public List<PersonInfo> selectAllOrderBy(int age);

	// 多個欄位之排序，每個欄位皆可設定排序方法
	@Query(value = "select P from PersonInfo as P where age > ?1 order by age asc, name desc")
	public List<PersonInfo> selectAllOrderBy2(int age);
	
	// limit 回傳比數: 只能用於nativeQuery = true
	@Query(value = "select * from person_info where age > ?1 order by age limit 5", //
			nativeQuery = true)
	public List<PersonInfo> selectAllOrderByLimit(int age);
	
	// limit index, 回傳比數: 從index位置開始抓取資料(index從0開始)
	@Query(value = "select * from person_info where age > ?1 order by age limit 1, 5", //
			nativeQuery = true)
	public List<PersonInfo> selectAllOrderByLimit2(int age);
	
	// where 欄位名稱 between value1 and value2: 查詢資料值介於value1跟value2之間(連續的值)
	@Query(value = "select * from person_info where age between ?1 and ?2", //
			nativeQuery = true)
	public List<PersonInfo> selectAllBetween(int age1, int age2);
	
	@Query(value = "select P from PersonInfo as P where age between ?1 and ?2")
	public List<PersonInfo> selectAllBetween2(int age1, int age2);
	
	// where 欄位名稱 in (value1, value2): 查詢資料值完全等於value1#或#value2的值
	@Query(value = "select P from PersonInfo as P where age in (?1)")
	public List<PersonInfo> selectAllIn(int age1, int age2);
	
	// 一般會用一個Collection(List或Set)的參數來表示多個值
	@Query(value = "select P from PersonInfo as P where age in (?1, ?2)")
	public List<PersonInfo> selectAllIn1(List<Integer> ageList);
	
	@Query(value = "select * from person_info where age in (?1, ?2)", //
			nativeQuery = true)
	public List<PersonInfo> selectAllIn2(int age1, int age2);
	
	// like要搭配%才會具有模糊比對的效果；%可視為0~多個任意字元
	// 不搭配%，是完全比對
	// 搜尋的關鍵字不區分大小寫
	// %北: 以北當結尾，前面是%，所以可以是0~多個字元；北、新北、台北都符合
	// 北%: 以北當開頭，後面的%可以是 0~多個字元
	// %北%: 只要有北的都符合
//	@Query(value = "select P from PersonInfo as P where city like %?1%")
	@Query(value = "select * from person_info where city like %?1%", //
			nativeQuery = true)
	public List<PersonInfo> selectAllByCityLike(String city);
	
	
	// regexp關鍵字: 其效果等同於like %關鍵字%
	// 使用在JPQL時，關鍵字前後不能加%，加了會報錯
	// 只能用於nativeQuery = true
	@Query(value = "select * from person_info where city regexp ?1", //
			nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp(String city);
	
	// 邏輯運算符號(|或&)不屬於SQL語法中認定的字符，用concat將參數串接
	// concat是將方法中的所有參數串接成字串，所以邏輯符號記得要加上單引號(SQL語法中的字串是用單引號)
	@Query(value = "select * from person_info where city regexp cancat (?1, |, ?2)", //
			nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp2(String city1, String city2);
	
	

//	----------------------------------------------------------------
	// join
	
	// 因為 JoinVo沒有被spring boot託管，所以前面需要加上完整路徑
	@Query("select new com.example.practice.vo.JoinVo(p.id, p.name, p.age, p.city, a.balance)"
			+ " from PersonInfo as p join Atm as a on p.id = a.acc")
	public List<JoinVo> joinTable();
	
	// 因為撈取的欄位是跨不同的表，沒有對應的 Entity 可以將資料 mapping 回來
	// 所以使用 Map<String, Object> 表示: key 是欄位名稱，value 是欄位對應的值
	// 因為取回來的資料可能會有多筆，用 List 接
	// 要解析回傳的結果，就一定使用雙層 for 迴圈，List 一層，Map 一層
	// join 不建議使用在 nativeQuery = true
	@Query(value = "select p.id, p.name, a.balance from person_info as p join atm as a "
			+ " on p.id = a.account", nativeQuery = true)
	public List<Map<String, Object>> joinTables();
	
	// join + where
	// where可以是表1或表2中任一欄位(若欄位名稱是唯一，可不指定)
	@Query("select new com.example.practice.vo.JoinVo(p.id, p.name, p.age, p.city, a.balance)"
			+ " from PersonInfo as p join Atm as a on p.id = a.acc where p.age > ?1")
	public List<JoinVo> joinTable2(int age);
	
	// join + limit: join建議使用nativeQuery = false，limit則只能使用於nativeQuery = true
	// 分頁(Pageable)就是指將所有符合條件的資料，分成每一頁有幾筆資料；例如: 資料有10筆，每頁3筆，總共就會有4頁
	// limit就是會把符合條件的資料回傳限制在幾筆，就是回傳前幾筆資料
	// 這樣的概念等同於把分頁中第1頁的資料回傳
	// 定義方法時使用Pageable，呼叫該方法時是使用PageRequest.of(int page_index, int size)
	// page_index: 從0開始，即我們所謂的第一頁
	// size: 每頁有幾筆資料
	// PageRequest.of(0, 5): 表示要回傳前5筆資料，等同於 limit 5
	// Pageable import 的 library: org.springframework.data.domain.Pageable###########
	@Query("select new com.example.practice.vo.JoinVo(p.id, p.name, p.age, p.city, a.balance)"
			+ " from PersonInfo as p join Atm as a on p.id = a.acc")
	public List<JoinVo> joinTableWithLimit(Pageable pageable);
	
}