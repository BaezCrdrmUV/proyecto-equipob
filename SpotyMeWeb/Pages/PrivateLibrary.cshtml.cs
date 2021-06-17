using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class PrivateLibraryModel : PageModel
    {
        private readonly ILogger<PrivateLibraryModel> _logger;

        public PrivateLibraryModel(ILogger<PrivateLibraryModel> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}
