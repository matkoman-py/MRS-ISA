package usijani.momci.pharmacyhub;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import pharmacyhub.PharmacyhubApplication;
import pharmacyhub.dto.complaint.MakeReplyDto;
import pharmacyhub.services.complaints.ComplaintService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PharmacyhubApplication.class })
public class ComplaintServiceTests {

	@Autowired
	private ComplaintService complaintService;

	@Test(expected = PessimisticLockingFailureException.class)
	public void testPessimisticLockingScenario() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");
				MakeReplyDto dto = new MakeReplyDto("11038a9a-a754-11eb-bcbc-0242ac130002", "2d2eacbf-fda8-4a50-aa05-be2be4fb2884", "Prvi");
					try {
						complaintService.makeReply(dto);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

			}
		});
		Future<?> future2 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
				MakeReplyDto dto = new MakeReplyDto("11038a9a-a754-11eb-bcbc-0242ac130002", "2d2eacbf-fda8-4a50-aa05-be2be4fb2884", "Drugi");
				try {
					complaintService.makeReply(dto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		try {
			future2.get();
		} catch (ExecutionException e) {
			System.out.println("Exception from thread " + e.getCause().getClass());
			throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
