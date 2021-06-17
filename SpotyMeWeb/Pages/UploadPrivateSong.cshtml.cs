using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class UploadPrivateSongModel : PageModel
    {
        private readonly ILogger<UploadPrivateSongModel> _logger;

        public UploadPrivateSongModel(ILogger<UploadPrivateSongModel> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}