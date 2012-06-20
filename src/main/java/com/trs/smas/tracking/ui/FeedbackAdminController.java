package com.trs.smas.tracking.ui;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.trs.smas.tracking.bo.Feedback;
import com.trs.smas.tracking.model.feedback.IFeedbackAdminService;


@Controller
@RequestMapping(value="/feedback")
public class FeedbackAdminController {

	private IFeedbackAdminService feedbackAdminService;

	public void setFeedbackAdminService(IFeedbackAdminService feedbackAdminService) {
		this.feedbackAdminService = feedbackAdminService;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
		ModelAndView mv = new ModelAndView("/feedback/list");
		mv.addObject("feedback",feedbackAdminService.pageList(pageNo, pageSize));
		return mv;
	}

	@RequestMapping(value = "/createform", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("/feedback/createform");
		mv.addObject(new Feedback());
		return mv;
	}

	@RequestMapping( method = RequestMethod.POST)
	public String add(@Valid Feedback feedback, BindingResult result) {
		if(result.hasErrors()){
			return "/feedback/createform";
		}
		feedbackAdminService.add(feedback);
		return "redirect:/feedback";
	}

	@RequestMapping(value = "/{feedbackId}/editform", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable int feedbackId) {
		ModelAndView mv = new ModelAndView("/feedback/editform");
		mv.addObject(feedbackAdminService.get(feedbackId));
		return mv;
	}

	@RequestMapping(value = "/{feedbackId}", method = RequestMethod.PUT)
	public String update(@PathVariable int feedbackId, @Valid Feedback feedback,
			BindingResult result) {
		if(result.hasErrors()){
			return "/feedback/editform";
		}
		
		Feedback origin = feedbackAdminService.get(feedbackId);
		if(origin != null){
			origin.setDescription(feedback.getDescription());
			feedbackAdminService.update(origin);
		}
		return "redirect:/feedback";
	}

	@RequestMapping(value = "/{feedbackId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable int feedbackId) {
		ModelAndView mv = new ModelAndView("/feedback/detail", "feedback",
				feedbackAdminService.get(feedbackId));
		return mv;
	}

	@RequestMapping(value = "/{feedbackId}", method = RequestMethod.DELETE)
	public String remove(@PathVariable int feedbackId) {
		feedbackAdminService.remove(feedbackId);
		return "redirect:/feedback";
	}
	
}
