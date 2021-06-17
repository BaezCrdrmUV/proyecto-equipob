using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class SpotyMeHomeModel : PageModel
    {
        private readonly ILogger<SpotyMeHomeModel> _logger;

        public SpotyMeHomeModel(ILogger<SpotyMeHomeModel> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}