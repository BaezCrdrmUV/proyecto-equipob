using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class SongSearch : PageModel
    {
        private readonly ILogger<SongSearch> _logger;

        public SongSearch(ILogger<SongSearch> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}
