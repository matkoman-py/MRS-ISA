package pharmacyhub.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pharmacyhub.dto.complaint.ComplaintDto;
import pharmacyhub.dto.complaint.MakeComplaintDto;
import pharmacyhub.dto.complaint.MakeReplyDto;
import pharmacyhub.dto.complaint.ReplyDto;
import pharmacyhub.services.complaints.ComplaintService;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ComplaintDto>> getComplaints(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		Pageable pageable = (page == null || size == null) ? Pageable.unpaged() : PageRequest.of(page, size);
		return new ResponseEntity<>(complaintService.getComplaints(pageable), HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ComplaintDto> makeComplaint(@RequestBody MakeComplaintDto makeComplaintDto) {
		return new ResponseEntity<>(complaintService.makeComplaint(makeComplaintDto), HttpStatus.OK);
	}
	
	@GetMapping(path = "/reply/{complaintId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyDto> getReply(@PathVariable("complaintId") String complaintId) throws Exception {
		return new ResponseEntity<>(complaintService.getReply(complaintId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/reply/{complaintId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyDto> getReply(@RequestBody MakeReplyDto makeReplyDto) throws Exception {
		return new ResponseEntity<>(complaintService.makeReply(makeReplyDto), HttpStatus.OK);
	}
}
