using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class MusicDataModel : PageModel
    {
        private readonly ILogger<MusicDataModel> _logger;

        public MusicDataModel(ILogger<MusicDataModel> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}