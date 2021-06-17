using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class RegisterAccountModel : PageModel
    {
        private readonly ILogger<RegisterAccountModel> _logger;

        public RegisterAccountModel(ILogger<RegisterAccountModel> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}
