using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace SpotyMeWeb.Pages
{
    public class UploadPublicSongModel : PageModel
    {
        private readonly ILogger<UploadPublicSongModel> _logger;

        public UploadPublicSongModel(ILogger<UploadPublicSongModel> logger)
        {
            _logger = logger;
        }

        public void OnGet()
        {

        }
    }
}