package com.example.btl_n35.dao;

import com.example.btl_n35.database.HibernateUtil;
import com.example.btl_n35.entity.Answer;
import com.example.btl_n35.entity.Question;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnswerDao implements InterfaceDAO<Answer> {
	private static AnswerDao instance;

	public static AnswerDao getInstance() {
		if (AnswerDao.instance == null)
			AnswerDao.instance = new AnswerDao();
		return AnswerDao.instance;
	}

	@Override
	public List<Answer> selectALl() {
		Transaction transaction = null;
		List<Answer> answers = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Answer.class);
			// selectAll Answer object
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			answers = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return answers;
	}

	@Override
	public Answer selectById(int id) {
		Transaction transaction = null;
		Answer answer = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById Answer object
			answer = session.get(Answer.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return answer;
	}

	@Override
	public void insert(Answer answer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save Answer object
			session.save(answer);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(Answer answer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update Awnser object
			session.saveOrUpdate(answer);
			// commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

//	@Override
//	public void delete(int id) {
//		Transaction transaction = null;
//		Answer answer = null;
//
//		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//			// start the transaction
//			transaction = session.beginTransaction();
//			answer = session.get(Answer.class, id);
//			// delete Answer object
//			session.delete(answer);
//			// commit the transaction
//			transaction.commit();
//
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//		}
//
//	}
@Override
public void delete(int id) {
	Transaction transaction = null;

	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		transaction = session.beginTransaction();

		// Tìm câu trả lời theo id
		Answer answer = session.get(Answer.class, id);

		if (answer != null) {
			// Lấy khóa chính của câu hỏi liên quan
			int questionId = answer.getQuestion().getId();

			// Tìm câu hỏi dựa trên khóa chính
			Question question = session.get(Question.class, questionId);

			if (question != null) {
				// Xóa câu trả lời khỏi danh sách câu trả lời của câu hỏi
				question.getAnswers().remove(answer);

				// Xóa câu trả lời
				session.delete(answer);

				// Cập nhật câu hỏi sau khi xóa câu trả lời
				session.update(question);

				transaction.commit();
			}
		}
	} catch (Exception e) {
		if (transaction != null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
}

}
