using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class AdminMenu : PageModel
    {
        private readonly ILogger<AdminMenu> _logger;

        public AdminMenu(ILogger<AdminMenu> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}
