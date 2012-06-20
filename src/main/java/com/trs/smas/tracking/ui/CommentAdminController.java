package com.trs.smas.tracking.ui;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.trs.smas.tracking.bo.Comment;
import com.trs.smas.tracking.model.comment.ICommentAdminService;

@Controller
@RequestMapping(value = "/feedback/{feedbackId}/comments")
public class CommentAdminController {

	private ICommentAdminService commentAdminService;

	public void setCommentAdminService(ICommentAdminService commentAdminService) {
		this.commentAdminService = commentAdminService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(
			@PathVariable int feedbackId,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
		ModelAndView mv = new ModelAndView("/comment/list");
		//TODO check feedback
		mv.addObject("comments",
				commentAdminService.pageList(feedbackId, pageNo, pageSize));
		mv.addObject("feedbackId", feedbackId);
		return mv;
	}

	@RequestMapping(value = "/createform", method = RequestMethod.GET)
	public ModelAndView create(@PathVariable int feedbackId) {
		ModelAndView mv = new ModelAndView("/comment/createform");
		//TODO check feedback
		Comment comment = new Comment();
		comment.setFeedbackId(feedbackId);
		mv.addObject(comment);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@PathVariable int feedbackId, @Valid Comment comment,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/comment/createform";
		}
		comment.setFeedbackId(feedbackId);
		commentAdminService.add(comment);
		return "redirect:/feedback/" + feedbackId + "/comments";
	}

	@RequestMapping(value = "/{commentId}/editform", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable int feedbackId,
			@PathVariable int commentId) {
		ModelAndView mv = new ModelAndView("/comment/editform");
		// TODO get by feedbackId and commentId ?
		mv.addObject(commentAdminService.get(commentId));
		return mv;
	}

	@RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
	public String update(@PathVariable int feedbackId,
			@PathVariable int commentId, @Valid Comment comment,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/comment/editform";
		}

		// TODO check feedbackId ?
		Comment origin = commentAdminService.get(commentId);
		origin.setContent(comment.getContent());
		commentAdminService.update(origin);
		return "redirect:/feedback/" + feedbackId + "/comments";
	}

	@RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable int feedbackId,
			@PathVariable int commentId) {
		// TODO get by feedbackId and commentId ?
		ModelAndView mv = new ModelAndView("/comment/detail", "comment",
				commentAdminService.get(commentId));
		return mv;
	}

	@RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
	public String remove(@PathVariable int feedbackId,
			@PathVariable int commentId) {
		// TODO check feedbackId ?
		commentAdminService.remove(commentId);
		return "redirect:/feedback/" + feedbackId + "/comments";
	}

}
