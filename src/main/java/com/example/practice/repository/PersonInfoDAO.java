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

	// OrderBy: �Ƨ�-Asc �p��j(�w�]) Desc �j��p)
	// OrderBy: �N���������(�]�t�z���)�A�ھګ��w��찵�ƧǡA�GJPA�y�k��"�̫�"
	public List<PersonInfo> findByAgeLessThanEqualOrderByAge(int age);

	// �Y�Ͽz�����ۦP�A�C��LessThan/GreaterThan�e�����[�W�U�۪��ܼƦW��
	public List<PersonInfo> findByAgeLessThanEqualOrAgeGreaterThanEqual(int age1, int age2);

	// Between: �z��d��Oage1 <= x <= age2
	// ����^�Ǹ�Ƶ���: First/Top + �^�ǵ���(�y�k��m�g�bfind����)
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int age1, int age2);

	// Containing: ����r�j�M�z��A�y�k��m�g�b�ܼƦW�٤���A�L�k��������r�A�h���r��j�M�N�|�O���@��
	public List<PersonInfo> findByCityContaining(String keyword);

	// Containing�i�Ω�h���j�M�z��(Or/And)
	public List<PersonInfo> findByCityContainingOrNameContaining(String keywordCity, String keywordName);

	// Like�G ����j�M�A�z���Ʊ���city == keyword
	public List<PersonInfo> findByCityLike(String keyword);

	public List<PersonInfo> findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(int age, String keyword);

	// In: ��Ƿj�M�A�A�Ω�P���B�h�Ӥ��P����r���j�M�z��Aex: �i�P�ɿz��j�M"�̪F"�B"����"...
	public List<PersonInfo> findByCityIn(List<String> keywordList);

	
	
//	----------------------------------------------------------------
	// JPQL
	// JPQL�AnativeQuery�w�]�Ofalse
	// nativeQuery = false�ɡA�y�k������M���W�٦U�O�ާ@Entity class�W�٩M�ݩ��ܼƦW��
	// nativeQuery = true�ɡA�y�k������M���W�٬O�ϥθ�Ʈw����ƪ�(@Table�᭱���W�r)�M���(@Column�����W�r)���W��
	
	// ****insert �u��ϥΩ�nativeQuery = true****
	
	// ��k���^�ǫ��A��int���A�i�ΨӪ�ܷs�W���\��ƪ����ơA0�N��S���s�W������
	// values()����?�Ʀr�A�����O��kinsert()���Ѽƪ���m
	// JPQL: insert/update/delete�n�D�n�[�W@Transactional�M@Modifying
	// @Transactional import��library�Ajavax �Mspringframework���i�H�ϥΨ�̮t���i�ѷ�PPT spring boot_02 @Transactional����

	
	// insert: �u��s�W��T�A�Y���n�s�W����T��PK����s�b��DB�A�Y�w�s�b�|����
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
	// �^�ǫ��Aint��ܸ�Ƨ�s���\��ơA0��ܨS�������Ƨ�s���\
	// where~�@�w�n�[�W�A�_�h�|��s�Ҧ����
	// JPQL: insert/update/delete�n�D�n�[�W@Transactional�M@Modifying
	
	
	// update
	@Modifying
	@Transactional
	@Query(value = "update person_info set city = ?1 where id = ?2", nativeQuery = true)
	public int updateCityById(String city, String id);
	
	// nativeQuery = false�ɡA�y�k������M���W�٦U�O�ާ@Entity class�W�٩M�ݩ��ܼƦW��
	// nativeQuery = false�O�w�]�A�ҥH�i�H���μg
	@Modifying
	@Transactional
	@Query(value = "update PersonInfo set city = ?1 where id = ?2", nativeQuery = false)
	public int updateCityById2(String city, String id);

	// P.name = case when ?2 is null then P.name else ?2
	// ���q�����k��: �Y?2���ȬOnull�ɡA��P.name����(��l��)�]�w�����name
	// else ?2: �_�h�N?2(�~����)���ȳ]�w�����name
	@Modifying
	@Transactional
	@Query(value = "update PersonInfo as P set "
			+ " P.name = case when ?2 is null then P.name else ?2 end, "
			+ " P.age = case when ?3 <=0 then P.age else ?3 end, "
			+ " P.city = case when ?4 is null then P.city else ?4 end where id = ?1", //
	nativeQuery = false)
	public int updateInfo(String id, String name, int age, String city);
	
	// coalesce�u�|�P�_null
	// �i�H���h�ӰѼơA�|�qvalue1�}�l��A�ê�^#�Ĥ@�ӫDnull#���ѼƭȡA�Y�����ѼƬ�null�h�^��null
	// �ƭȪ��P�_�@�˥�case when
	@Modifying
	@Transactional
	@Query(value = "update PersonInfo as P set "
			+ " P.name = coalesce(?2, P.name), "
			+ " P.age = case when ?3 <=0 then P.age else ?3 end, "
			+ " P.city = coalesce(?4, P.city) where id = ?1", //
	nativeQuery = false)
	public int updateInfo2(String id, String name, int age, String city);
	
	
	
//	----------------------------------------------------------------
	// JPQL: insert/update/delete�n�D�n�[�W@Transactional�M@Modifying
	
	
	// delete
	@Modifying
	@Transactional
	@Query(value = "delete from person_info where id = ?1", nativeQuery = true)
	public int deleteById2(String id);
	

	
//	----------------------------------------------------------------
	// select
	// *�N��Ҧ����A�u��Ω�nativeQuery = true
	@Query(value = "select * from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll();
	
	// ����*
	@Query(value = "select id, name, age, city from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll2();
	
	// �d�߳������A�u��ϥΦbnativeQuery = false
	// select�᭱�n�����������쥲���n��#�غc��k#���g�k: new���O�W��(�ݩʦW��)
	// new PersonInfo(id, name, age, city): �o�ӫغc��k�@�w�o�n�s�b�A�~��b�y�k���ϥ�
	// �N�O�ݻy�k���|�Ψ켴�X�ӰѼơA��������غc��k�N�o�n�s�b
	// ���Q���������A�|�ᤩ�w�]�ȡA�Ҧpcity���b��������줤�A�ҥH���G����city�������|�O�w�]��null
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo")
	public List<PersonInfo> selectAll21();
	
	// �ϥΧO�W(Alias): as�i�H�ٲ��AP���W�٥��N�R�W�A�ΨӥN����Entity
	// P���P��ϥΩҦ���쪺�غc��k�Anew PersonInfo(id, name, age, city)
	@Query(value = "select P from PersonInfo as P")
	public List<PersonInfo> selectAll22();
	
	// distinct: �Ψӿz�ﭫ�ƪ�����ƭȡA���P��Collection����Set�A�ۦP�ȥu�O�d�@��
	// �Ydistinct�᭱����즳�h�ӡA��ܿz��o������_�Ӫ���
	@Query(value = "select distinct city from PersonInfo")
	public List<String> selectDistinct();
	
	@Query(value = "select distinct new PersonInfo(name, city) from PersonInfo")
	public List<PersonInfo> selectDistinct2();
	
	// �H�U2�جO�ϥ�nativeQuery = true�ɪ��g�k�A���̫�n���Ȯɳ��S��nativeQuery = false�ɨӪ�²��
	// �ҥH��ĳ�ϥ�nativeQuery = false���g�k
	@Query(value = "select distinct name, city from person_info", nativeQuery = true)
	public List<Map<String, String>> selectDistinct3();
	
	@Query(value = "select distinct name, city from person_info", nativeQuery = true)
	public List<Object> selectDistinct4();
	
	// �d�߳������: �H�U��k�|�����AnativeQuery = true�ݼg�X�������
	@Query(value = "select id, name, age from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll3();
	
	// ������d�߳������
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where age > ?1")
	public List<PersonInfo> selectAll31(int age);


	// ������d�߳������
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where age != ?1")
	public List<PersonInfo> selectAll32(int age);

	// ������d�߳������
	// �U��SQL�y�y���A���涶�Ǭ�: 1. from  2. where  3. select
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where city != ?1")
	public List<PersonInfo> selectAll33(String id);
	
	// ������: <> / !=
	@Query(value = "select new PersonInfo(id, name, age) from PersonInfo where city <> ?1")
	public List<PersonInfo> selectAll34(String id);
	
	// order by ���W�� �ƧǤ�k( ASC | DESC ): �ƧǤ�k�w�]��ASC�A�i�����g
	// �Y�Ƨ���쪺��즳�ۦP�ȮɡA�|�A�̷�PK�Ƨ�(ASC)
	@Query(value = "select P from PersonInfo as P where age > ?1 order by age asc")
	public List<PersonInfo> selectAllOrderBy(int age);

	// �h����줧�ƧǡA�C�����ҥi�]�w�ƧǤ�k
	@Query(value = "select P from PersonInfo as P where age > ?1 order by age asc, name desc")
	public List<PersonInfo> selectAllOrderBy2(int age);
	
	// limit �^�Ǥ��: �u��Ω�nativeQuery = true
	@Query(value = "select * from person_info where age > ?1 order by age limit 5", //
			nativeQuery = true)
	public List<PersonInfo> selectAllOrderByLimit(int age);
	
	// limit index, �^�Ǥ��: �qindex��m�}�l������(index�q0�}�l)
	@Query(value = "select * from person_info where age > ?1 order by age limit 1, 5", //
			nativeQuery = true)
	public List<PersonInfo> selectAllOrderByLimit2(int age);
	
	// where ���W�� between value1 and value2: �d�߸�ƭȤ���value1��value2����(�s�򪺭�)
	@Query(value = "select * from person_info where age between ?1 and ?2", //
			nativeQuery = true)
	public List<PersonInfo> selectAllBetween(int age1, int age2);
	
	@Query(value = "select P from PersonInfo as P where age between ?1 and ?2")
	public List<PersonInfo> selectAllBetween2(int age1, int age2);
	
	// where ���W�� in (value1, value2): �d�߸�ƭȧ�������value1#��#value2����
	@Query(value = "select P from PersonInfo as P where age in (?1)")
	public List<PersonInfo> selectAllIn(int age1, int age2);
	
	// �@��|�Τ@��Collection(List��Set)���ѼƨӪ�ܦh�ӭ�
	@Query(value = "select P from PersonInfo as P where age in (?1, ?2)")
	public List<PersonInfo> selectAllIn1(List<Integer> ageList);
	
	@Query(value = "select * from person_info where age in (?1, ?2)", //
			nativeQuery = true)
	public List<PersonInfo> selectAllIn2(int age1, int age2);
	
	// like�n�f�t%�~�|�㦳�ҽk��諸�ĪG�F%�i����0~�h�ӥ��N�r��
	// ���f�t%�A�O�������
	// �j�M������r���Ϥ��j�p�g
	// %�_: �H�_�����A�e���O%�A�ҥH�i�H�O0~�h�Ӧr���F�_�B�s�_�B�x�_���ŦX
	// �_%: �H�_��}�Y�A�᭱��%�i�H�O 0~�h�Ӧr��
	// %�_%: �u�n���_�����ŦX
//	@Query(value = "select P from PersonInfo as P where city like %?1%")
	@Query(value = "select * from person_info where city like %?1%", //
			nativeQuery = true)
	public List<PersonInfo> selectAllByCityLike(String city);
	
	
	// regexp����r: ��ĪG���P��like %����r%
	// �ϥΦbJPQL�ɡA����r�e�ᤣ��[%�A�[�F�|����
	// �u��Ω�nativeQuery = true
	@Query(value = "select * from person_info where city regexp ?1", //
			nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp(String city);
	
	// �޿�B��Ÿ�(|��&)���ݩ�SQL�y�k���{�w���r�šA��concat�N�ѼƦ걵
	// concat�O�N��k�����Ҧ��ѼƦ걵���r��A�ҥH�޿�Ÿ��O�o�n�[�W��޸�(SQL�y�k�����r��O�γ�޸�)
	@Query(value = "select * from person_info where city regexp cancat (?1, |, ?2)", //
			nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp2(String city1, String city2);
	
	

//	----------------------------------------------------------------
	// join
	
	// �]�� JoinVo�S���Qspring boot�U�ޡA�ҥH�e���ݭn�[�W������|
	@Query("select new com.example.practice.vo.JoinVo(p.id, p.name, p.age, p.city, a.balance)"
			+ " from PersonInfo as p join Atm as a on p.id = a.acc")
	public List<JoinVo> joinTable();
	
	// �]�����������O�󤣦P����A�S�������� Entity �i�H�N��� mapping �^��
	// �ҥH�ϥ� Map<String, Object> ���: key �O���W�١Avalue �O����������
	// �]�����^�Ӫ���ƥi��|���h���A�� List ��
	// �n�ѪR�^�Ǫ����G�A�N�@�w�ϥ����h for �j��AList �@�h�AMap �@�h
	// join ����ĳ�ϥΦb nativeQuery = true
	@Query(value = "select p.id, p.name, a.balance from person_info as p join atm as a "
			+ " on p.id = a.account", nativeQuery = true)
	public List<Map<String, Object>> joinTables();
	
	// join + where
	// where�i�H�O��1�Ϊ�2�����@���(�Y���W�٬O�ߤ@�A�i�����w)
	@Query("select new com.example.practice.vo.JoinVo(p.id, p.name, p.age, p.city, a.balance)"
			+ " from PersonInfo as p join Atm as a on p.id = a.acc where p.age > ?1")
	public List<JoinVo> joinTable2(int age);
	
	// join + limit: join��ĳ�ϥ�nativeQuery = false�Alimit�h�u��ϥΩ�nativeQuery = true
	// ����(Pageable)�N�O���N�Ҧ��ŦX���󪺸�ơA�����C�@�����X����ơF�Ҧp: ��Ʀ�10���A�C��3���A�`�@�N�|��4��
	// limit�N�O�|��ŦX���󪺸�Ʀ^�ǭ���b�X���A�N�O�^�ǫe�X�����
	// �o�˪��������P����������1������Ʀ^��
	// �w�q��k�ɨϥ�Pageable�A�I�s�Ӥ�k�ɬO�ϥ�PageRequest.of(int page_index, int size)
	// page_index: �q0�}�l�A�Y�ڭ̩ҿת��Ĥ@��
	// size: �C�����X�����
	// PageRequest.of(0, 5): ��ܭn�^�ǫe5����ơA���P�� limit 5
	// Pageable import �� library: org.springframework.data.domain.Pageable###########
	@Query("select new com.example.practice.vo.JoinVo(p.id, p.name, p.age, p.city, a.balance)"
			+ " from PersonInfo as p join Atm as a on p.id = a.acc")
	public List<JoinVo> joinTableWithLimit(Pageable pageable);
	
}